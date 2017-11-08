from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from api.models import Readings
from api.serializers import ReadingsSerializer


@api_view(['GET', 'POST'])
def readings_list(request,format=None):
    """
    List all code snippets, or create a new snippet.
    """
    if request.method == 'GET':
        readings = Readings.objects.all()
        serializer = ReadingsSerializer(readings, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = ReadingsSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
@api_view(['GET', 'PUT', 'DELETE'])
def readings_detail(request, pk, format=None):
    """
    Retrieve, update or delete a code snippet.
    """
    try:
        readings = Readings.objects.get(pk=pk)
    except Readings.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = ReadingsSerializer(readings)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = ReadingsSerializer(snippet, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        readings.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

# Create your views here.
