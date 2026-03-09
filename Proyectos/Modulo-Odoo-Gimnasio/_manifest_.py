# -*- coding: utf-8 -*-
{
    'name': "jfg_modulo_gimnasio",

    'summary': "Práctica de creación de un módulo para un gimnasio",

    'description': """
        La práctica ejemplifica un módulo de un gimnasio con manejo de clientes, actividades y clientes
    """,

    'author': "Jesús Garay Franco",

    # Categories can be used to filter modules in modules listing
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        'security/security.xml',
        'security/ir.model.access.csv',
        'views/menu.xml',
        'views/views.xml',
        'views/templates.xml',
        'data/data.xml'
    ],
    "installable": True,
    "application": True,
    "license":"LGPL-3",
    
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
    ],
}