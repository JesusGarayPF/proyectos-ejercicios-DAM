from odoo import models, fields

class Actividad(models.Model):
    _name = 'jfg_modulo_gimnasio.actividad'
    _description = 'Gimnasio - Actividad'

    nombre = fields.Char(string='Nombre de la Actividad', required=True, help='Nombre de la actividad')
    duracion_mins = fields.Integer(string='Duración (Minutos)', help='Duración de la actividad en minutos')
    dia_semana = fields.Selection(
        [('monday', 'Lunes'), ('tuesday', 'Martes'), ('wednesday', 'Miércoles'), ('thursday', 'Jueves'),
         ('friday', 'Viernes'), ('saturday', 'Sábado'), ('sunday', 'Domingo')],
        string='Día de la Semana', required=True, help='Día de la semana en que se realiza la actividad'
    )
    hora_inicio = fields.Float(string='Hora de Inicio', help='Hora de inicio de la actividad (en formato 24h)')
    clientes_ids = fields.Many2many('gym.cliente', string='Clientes Inscritos', help='Clientes inscritos a la actividad')