from odoo import models, fields

class Membresia(models.Model):
    _name = 'jgf_gym.membresia'
    _description = 'Gimnasio - Membresía'

    nombre = fields.Char(string='Nombre del Plan', required=True, help='Nombre del plan de membresía')
    precio = fields.Float(string='Precio', required=True, help='Precio del plan')
    duracion_meses = fields.Integer(string='Duración (Meses)', required=True, help='Duración en meses del plan')
    activo = fields.Boolean(string='Activo', default=True, help='Indica si el plan está activo')