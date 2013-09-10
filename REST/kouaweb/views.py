from decimal import InvalidOperation
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import authentication, permissions
from django.http import HttpResponse
from django.core.exceptions import PermissionDenied
from rest_framework.parsers import JSONParser
from django.views.decorators.csrf import csrf_exempt
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
import json

from kouaweb.models import *
from kouaweb.utils import MessageJsonUtil


class MessageREST(APIView):
    def get(self, request, format=None):
        raise PermissionDenied

    def post(self, request, format=None):
        """
        Validating json format
        """
        try:
            json_request = JSONParser().parse(request)
        except Exception:
            return JSONResponse(json.dumps({'response_message':'json format error'}), status=500)

        """
        Validating decimal format and date
        """
        try:
            json_util = MessageJsonUtil(json_request)
        except InvalidOperation:
            return JSONResponse(json.dumps({'response_message':'Cannot convert string to decimal value (monto)'}), status=500)
        except ValueError:
            return JSONResponse(json.dumps({'response_message':'Invalid date format'}), status=500)

        message = json_util.get_object()

        json_response = []
        status_code = 200

        if message.is_valid():
            try:
                obj  = Message.objects.get(id=message.id)
                message.update()
                json_response = {'response_message': 'message has been updated correctly'}
                status_code = 200
            except Message.DoesNotExist:
                message.save()
                json_response = {'response_message':'message has been stored correctly'}
                status_code = 200
        else:
            json_response = {'response_message':'there are empty required fields'}
            status_code = 500

        return JSONResponse(json.dumps(json_response), status=status_code)

class JSONResponse(HttpResponse):
    """
    An HttpResponse that renders its content into JSON.
    """
    def __init__(self, json_request, **kwargs):
        content = JSONRenderer().render(json_request)
        kwargs['content_type'] = 'application/json'
        super(JSONResponse, self).__init__(content, **kwargs)


"""
Do not use the following methods on urls.py,
those are for experimental only pruppose
"""

from rest_framework.decorators import api_view
@api_view(['GET'])
def get(request):
    raise PermissionDenied

@api_view(['POST'])
@csrf_exempt
def post(request):
    return Response({"message": "Hello, world!"})
