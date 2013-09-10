from django.db import models

# Create your models here.

class Message(models.Model):
    operacion = models.CharField(max_length=400)
    monto = models.DecimalField(max_digits=10, decimal_places=2)
    autorizacion = models.CharField(max_length=200)
    fecha = models.DateField()
    descripcion = models.CharField(max_length=200)
    geolocalizacion = models.CharField(max_length=500)

    """
    Falta el atributo 'cuenta'
    """
    def is_valid(self):
        return self.operacion != "" and self.monto != "" and self.fecha is not None
