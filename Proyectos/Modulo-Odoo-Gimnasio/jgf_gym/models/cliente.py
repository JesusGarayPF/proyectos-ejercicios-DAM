from odoo import models, fields

class Cliente(models.Model):
    _name = 'jfg_modulo_gimnasio.cliente'
    _description = 'Gimnasio - Cliente'

    nombre = fields.Char(string='Nombre', required=True, help='Nombre del cliente')
    apellido = fields.Char(string='Apellido', required=True, help='Apellido del cliente')
    email = fields.Char(string='Email', required=True, help='Correo electrónico del cliente')
    telefono = fields.Char(string='Teléfono', help='Teléfono del cliente')
    fecha_nac = fields.Date(string='Fecha de Nacimiento', help='Fecha de nacimiento del cliente')
    membresia_id = fields.Many2one('gym.membresia', string='Membresía', help='Membresía activa del cliente')
    actividades_ids = fields.Many2many('gym.actividad', string='Actividades Inscritas', help='Actividades en las que participa el cliente')


