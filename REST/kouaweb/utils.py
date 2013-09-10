from models import Message
from decimal import Decimal, InvalidOperation
import models
import datetime

class MessageJsonUtil:
    def __init__(self, data=None):
        self.parse(data)

    def parse(self, data=None):
        if data is not None:
            self.message = Message()
            self.message.operacion = data["operacion"]

            try:
                self.message.monto = Decimal(data["monto"])
            except InvalidOperation as e:
                raise e

            self.message.autorizacion = data["autorizacion"]

            try:
                self.message.fecha = datetime.datetime.strptime(data["fecha"], '%m/%d/%Y').date()
            except ValueError as e:
                raise e

            self.message.descripcion = data["descripcion"]
            self.message.geolocalizacion = data["geolocalizacion"]

    def get_object(self):
        return self.message
