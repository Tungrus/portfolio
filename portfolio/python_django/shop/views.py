from PIL import Image
from yandex_money.api import Wallet

from CarPartsShop.settings import YANDEX_ID
from shop.models import Post, Comment, Categories, Basket, PostVote
from .forms import CommentForm, PostForm, VoteForm, choises
from django.contrib.auth import login, authenticate, logout
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.shortcuts import render, redirect


def posts_lists_view(request):
    posts = 1
    if 'category' not in request.GET:
        posts = Post.objects.all()
    else:
        category = request.GET["category"]
        if category in Categories.categories_dict:
            posts = Post.objects.filter(category=category)
    i = 0
    post_groups = []
    new_posts = []
    for post in posts:
        i += 1
        post_groups.append(post)
        if i % 3 == 0:
            new_posts.append(post_groups)
            post_groups = []
    new_posts.append(post_groups)
    return render(request, "shop/post_list.html", {'posts': new_posts})


def post_detail_view(request, pk):
    post = Post.objects.filter(pk=pk)
    vote = PostVote.objects.filter(post=post[0])
    total_vote = "нет голосов"
    if vote[0].count_of_voters != 0:
        total_vote = vote[0].total_count / vote[0].count_of_voters
    return render(request, 'shop/post_detail.html', {'post': post[0], 'marks': choises, 'total_vote': total_vote})


def add_comment(request, pk):
    if request.method == 'POST':
        form = CommentForm(request.POST)
        if form.is_valid():
            comment = Comment(context=form.cleaned_data['context'], post=Post.objects.get(pk=pk), user=request.user)
            comment.save()
    else:
        form = CommentForm()
    return redirect('/shop/' + pk + '/')


def add_post(request):
    if request.method == 'POST':
        form = PostForm(request.POST, request.FILES)
        if form.is_valid():
            post = Post(content=form.cleaned_data['context'], title=form.cleaned_data['title'],
                      category=form.cleaned_data['category'], price=form.cleaned_data['price'],
                      image=form.cleaned_data['image'])
            post.save()
            PostVote(post=post).save()
            return redirect(to='/shop/')
        
    return render(request, 'shop/add_post.html', {'categories': Categories.categories})


def new_post(request):
    return redirect(to='/shop/addPost')


def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            form.save()
            username = form.cleaned_data.get('username')
            raw_password = form.cleaned_data.get('password1')
            user = authenticate(username=username, password=raw_password)
            login(request, user)
            return redirect('/shop/')
    else:
        form = UserCreationForm()
    return render(request, 'shop/signup.html', {'form': form })


def login_user(request):
    if request.method == 'POST':
        form = AuthenticationForm(request.POST)
        if form.is_valid():
            authenticate(request)
        else:
            return render(request, '/shop/login', {"errors": form.error_messages, "form": AuthenticationForm()})

    return redirect('/shop/')


def login_user_form_show(request):
    form = AuthenticationForm()
    return render(request, 'shop/login.html', {'form': form})


def logout_user(request):
    logout(request)
    return redirect('/shop/')


def basket_view(request):
    orders = Basket.objects.filter(user=request.user)
    total_price = 0
    for order in orders :
        total_price += order.post.price

    return render(request, 'shop/basket.html', {'basket': orders, 'total_price': total_price})


def add_product_to_basket(request, pk):
    post = Post.objects.filter(pk=pk)
    basket = Basket(user=request.user, post=post[0])
    basket.save()
    return redirect('/shop/' + pk)


def basket_confirm(request):
    if 'is_confirm' in request.GET:
        is_confirm = request.GET['is_confirm']
        if is_confirm == 'yes':
           # scope = ['account-info', 'operation-history']
           # orders = Basket.objects.filter(user=request.user)
           # auth_url = Wallet.build_obtain_token_url(YANDEX_ID,
            #                                         "http://127.0.0.1:8000/pay_success", scope)
           # redirect(auth_url)
           # Basket.objects.filter(user=request.user).delete()
            return redirect(to="/shop/pay/")
        else:
            Basket.objects.filter(user=request.user).delete()
    return redirect('/shop/basket/')


def buy_confirm_finish(request):
    if 'code' in request.POST:
        code = request.POST['code']
        access_token = Wallet.get_access_token(YANDEX_ID, code, "http://127.0.0.1:8000/")


def pay_view(request):
    orders = Basket.objects.filter(user=request.user)
    total_price = 0
    for order in orders:
        total_price += order.post.price

    return render(request, 'shop/pay.html', {'total_price': total_price})


def add_vote_to_post(request, pk):
    form = VoteForm(request.POST)
    if form.is_valid():
        post = Post.objects.filter(pk=pk)
        vote = PostVote.objects.filter(post=post)
        vote.update(count_of_voters=vote.count_of_voters + 1, total_count= vote.total_count + form.cleaned_data.get('choice'))
    return render(request, 'shop/pk/')


