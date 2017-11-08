
from rest_framework import serializers
from api.models import Readings, LANGUAGE_CHOICES, STYLE_CHOICES


class ReadingsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Readings
        fields = ('created','x','id',)

    def create(self, validated_data):
        """
        Create and return a new `Snippet` instance, given the validated data.
        """
        return Readings.objects.create(**validated_data)

    def update(self, instance, validated_data):
        """
        Update and return an existing `Snippet` instance, given the validated data.
        """
        instance.x = validated_data.get('x', instance.x)
        instance.save()
        return instance
