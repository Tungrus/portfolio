from django.contrib import admin
from shop.models import Post, Comment, Basket, PostVote

# Register your models here.

admin.site.register(Post)
admin.site.register(Comment)
admin.site.register(Basket)
admin.site.register(PostVote)

