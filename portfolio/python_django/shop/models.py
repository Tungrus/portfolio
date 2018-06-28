import PIL
from PIL import Image
from django.contrib.auth.models import User
from django.core.files.uploadedfile import InMemoryUploadedFile
from django.db import models
from django.utils.six import StringIO


class Categories():
    all = "All"
    engines = "Engines"
    transmition = "Transmition"
    wheels = "Wheels"
    accessories = "Accessories"
    categories = [engines, transmition, wheels, accessories]
    categories_dict = {engines: engines, transmition: transmition, wheels: wheels, accessories: accessories}


class Post(models.Model):
    category = models.CharField(max_length=255)
    image = models.ImageField(upload_to='shop/uploads/')
    title = models.CharField(max_length=255)
    datetime = models.DateTimeField('publicationDate', auto_now_add=True)
    content = models.TextField(max_length=10000)
    price = models.DecimalField(max_digits=10, decimal_places=2)

    def __unicode__(self):
        return self.title
    
    def get_url(self):
        return "/shop/%i" % self.id
    
    def get_comments(self, **kwargs):
        return Comment.objects.filter(post=self.id)
    
    def save(self, *args, **kwargs):

        super().save(*args, **kwargs)
        
        
class Comment(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE)
    context = models.TextField(max_length=1000)
    user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    
    def save(self, *args, **kwargs):
        super().save(*args, **kwargs)
        
class Basket(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE)

    def delete(self):
        super().delete()

    def save(self, *args, **kwargs):
        super().save(*args, **kwargs)


class PostVote(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE)
    total_count = models.DecimalField(max_digits=10, decimal_places=2, default=0)
    count_of_voters = models.DecimalField(max_digits=10, decimal_places=2, default=0)

    def save(self, *args, **kwargs):
        super().save(*args, **kwargs)

class VotedUsersOnPost(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    mark = models.DecimalField(max_digits=10, decimal_places=2)



