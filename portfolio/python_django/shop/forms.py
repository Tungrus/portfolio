from django import forms


class CommentForm(forms.Form):
    context = forms.CharField(label='context', max_length=100)
    
    def get_context(self):
        return self.context
    
class PostForm(forms.Form):
    context = forms.CharField(label='context', max_length=10000)
    title = forms.CharField(label='title', max_length=255)
    category = forms.CharField(label='category', max_length=255)
    image = forms.ImageField(label='image')
    price = forms.IntegerField(label='price', min_value=0, max_value=2**32)

choises = [1, 2, 3, 4, 5]

class VoteForm(forms.Form):
    mark = forms.ChoiceField(label='choice', choices=choises)

