from django.db import models
from pygments.lexers import get_all_lexers
from pygments.styles import get_all_styles
from django.db.models import signals

LEXERS = [item for item in get_all_lexers() if item[1]]
LANGUAGE_CHOICES = sorted([(item[1][0], item[0]) for item in LEXERS])
STYLE_CHOICES = sorted((item, item) for item in get_all_styles())
def damn_funk(sender,instance,created,**kwargs):
	print ("One is saveD")
	getit(sender.objects.latest('id'))


class Readings(models.Model):

    created = models.DateTimeField(auto_now_add=True)
    x= models.TextField()


    class Meta:
        ordering = ('created',)
signals.post_save.connect(damn_funk,sender=Readings)

# Create your models here.
