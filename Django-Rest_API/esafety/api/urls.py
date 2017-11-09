from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from api import views

urlpatterns = [
    url(r'^api/$', views.readings_list),
    url(r'^api/(?P<pk>[0-9]+)$', views.readings_detail),
]

urlpatterns = format_suffix_patterns(urlpatterns)