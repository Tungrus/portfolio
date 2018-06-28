'''
Created on 23 ����. 2018 �.

@author: game
'''
from django.urls import re_path, path

from shop.views import logout_user, login_user, basket_view, add_product_to_basket, basket_confirm, \
    pay_view, add_vote_to_post, post_detail_view
from . import views


urlpatterns = [
    path(r'', view=views.posts_lists_view, name='list'),
    re_path(r'^(?P<pk>\d+)/$', post_detail_view, name='post-detail-view'),
    re_path(r'^(?P<pk>\d+)/newComment/$', view=views.add_comment, name='comment-send'),
    re_path(r'^addPost/$', view=views.add_post, name='post-add'),
    re_path(r'^newPost/$', view=views.new_post, name='new-post'),
    path('signup/', view=views.signup, name='siqnup'), 
    path('logout/', view=views.logout_user, name='logout'), 
    path('login/', view=views.login_user_form_show, name='login'),
    path('loginSend/', view=views.login_user, name='login_send'),
    path('basket/', view=basket_view , name='basket'),
    re_path(r'add_product_to_basket/(?P<pk>\d+)/$', view=add_product_to_basket, name='add_product_to_basket'),
    path('basket_confirm/', view=basket_confirm, name='basket_confirm'),
    path('pay/', view=pay_view, name="pay"),
    re_path(r'^(?P<pk>\d+)/newVote/$', view=add_vote_to_post, name="add-vote-to-post")
]