<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.btnRojo = New System.Windows.Forms.Button()
        Me.btnVerde = New System.Windows.Forms.Button()
        Me.btnAzul = New System.Windows.Forms.Button()
        Me.txtRojo = New System.Windows.Forms.TextBox()
        Me.txtVerde = New System.Windows.Forms.TextBox()
        Me.txtAzul = New System.Windows.Forms.TextBox()
        Me.txtResultado = New System.Windows.Forms.TextBox()
        Me.MenuStrip1 = New System.Windows.Forms.MenuStrip()
        Me.ProgresoToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.Barra1ToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.Barra2ToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.Barra3ToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.FondoToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ColorToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.DefaultToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.CerrarToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.txtAbcAzul = New System.Windows.Forms.TextBox()
        Me.txtAbcVerde = New System.Windows.Forms.TextBox()
        Me.txtAbcRojo = New System.Windows.Forms.TextBox()
        Me.Timer1 = New System.Windows.Forms.Timer(Me.components)
        Me.Timer2 = New System.Windows.Forms.Timer(Me.components)
        Me.Timer3 = New System.Windows.Forms.Timer(Me.components)
        Me.LinkLabel1 = New System.Windows.Forms.LinkLabel()
        Me.MenuStrip1.SuspendLayout()
        Me.SuspendLayout()
        '
        'btnRojo
        '
        Me.btnRojo.Location = New System.Drawing.Point(12, 45)
        Me.btnRojo.Name = "btnRojo"
        Me.btnRojo.Size = New System.Drawing.Size(63, 36)
        Me.btnRojo.TabIndex = 0
        Me.btnRojo.Text = "="
        Me.btnRojo.UseVisualStyleBackColor = True
        '
        'btnVerde
        '
        Me.btnVerde.Location = New System.Drawing.Point(12, 96)
        Me.btnVerde.Name = "btnVerde"
        Me.btnVerde.Size = New System.Drawing.Size(63, 36)
        Me.btnVerde.TabIndex = 1
        Me.btnVerde.Text = "="
        Me.btnVerde.UseVisualStyleBackColor = True
        '
        'btnAzul
        '
        Me.btnAzul.Location = New System.Drawing.Point(12, 145)
        Me.btnAzul.Name = "btnAzul"
        Me.btnAzul.Size = New System.Drawing.Size(63, 36)
        Me.btnAzul.TabIndex = 2
        Me.btnAzul.Text = "="
        Me.btnAzul.UseVisualStyleBackColor = True
        '
        'txtRojo
        '
        Me.txtRojo.Font = New System.Drawing.Font("Microsoft Sans Serif", 13.8!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtRojo.Location = New System.Drawing.Point(584, 45)
        Me.txtRojo.Multiline = True
        Me.txtRojo.Name = "txtRojo"
        Me.txtRojo.Size = New System.Drawing.Size(100, 34)
        Me.txtRojo.TabIndex = 3
        Me.txtRojo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'txtVerde
        '
        Me.txtVerde.Font = New System.Drawing.Font("Microsoft Sans Serif", 13.8!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtVerde.Location = New System.Drawing.Point(584, 96)
        Me.txtVerde.Multiline = True
        Me.txtVerde.Name = "txtVerde"
        Me.txtVerde.Size = New System.Drawing.Size(100, 36)
        Me.txtVerde.TabIndex = 4
        Me.txtVerde.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'txtAzul
        '
        Me.txtAzul.Font = New System.Drawing.Font("Microsoft Sans Serif", 13.8!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtAzul.Location = New System.Drawing.Point(584, 145)
        Me.txtAzul.Multiline = True
        Me.txtAzul.Name = "txtAzul"
        Me.txtAzul.Size = New System.Drawing.Size(100, 36)
        Me.txtAzul.TabIndex = 5
        Me.txtAzul.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'txtResultado
        '
        Me.txtResultado.Location = New System.Drawing.Point(12, 201)
        Me.txtResultado.Multiline = True
        Me.txtResultado.Name = "txtResultado"
        Me.txtResultado.Size = New System.Drawing.Size(672, 36)
        Me.txtResultado.TabIndex = 6
        '
        'MenuStrip1
        '
        Me.MenuStrip1.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.ProgresoToolStripMenuItem, Me.FondoToolStripMenuItem, Me.CerrarToolStripMenuItem})
        Me.MenuStrip1.Location = New System.Drawing.Point(0, 0)
        Me.MenuStrip1.Name = "MenuStrip1"
        Me.MenuStrip1.Size = New System.Drawing.Size(696, 28)
        Me.MenuStrip1.TabIndex = 7
        Me.MenuStrip1.Text = "MenuStrip1"
        '
        'ProgresoToolStripMenuItem
        '
        Me.ProgresoToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.Barra1ToolStripMenuItem, Me.Barra2ToolStripMenuItem, Me.Barra3ToolStripMenuItem})
        Me.ProgresoToolStripMenuItem.Name = "ProgresoToolStripMenuItem"
        Me.ProgresoToolStripMenuItem.Size = New System.Drawing.Size(81, 24)
        Me.ProgresoToolStripMenuItem.Text = "progreso"
        '
        'Barra1ToolStripMenuItem
        '
        Me.Barra1ToolStripMenuItem.Name = "Barra1ToolStripMenuItem"
        Me.Barra1ToolStripMenuItem.Size = New System.Drawing.Size(121, 24)
        Me.Barra1ToolStripMenuItem.Text = "barra1"
        '
        'Barra2ToolStripMenuItem
        '
        Me.Barra2ToolStripMenuItem.Name = "Barra2ToolStripMenuItem"
        Me.Barra2ToolStripMenuItem.Size = New System.Drawing.Size(121, 24)
        Me.Barra2ToolStripMenuItem.Text = "barra2"
        '
        'Barra3ToolStripMenuItem
        '
        Me.Barra3ToolStripMenuItem.Name = "Barra3ToolStripMenuItem"
        Me.Barra3ToolStripMenuItem.Size = New System.Drawing.Size(121, 24)
        Me.Barra3ToolStripMenuItem.Text = "barra3"
        '
        'FondoToolStripMenuItem
        '
        Me.FondoToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.ColorToolStripMenuItem, Me.DefaultToolStripMenuItem})
        Me.FondoToolStripMenuItem.Name = "FondoToolStripMenuItem"
        Me.FondoToolStripMenuItem.Size = New System.Drawing.Size(61, 24)
        Me.FondoToolStripMenuItem.Text = "fondo"
        '
        'ColorToolStripMenuItem
        '
        Me.ColorToolStripMenuItem.Name = "ColorToolStripMenuItem"
        Me.ColorToolStripMenuItem.Size = New System.Drawing.Size(125, 24)
        Me.ColorToolStripMenuItem.Text = "color"
        '
        'DefaultToolStripMenuItem
        '
        Me.DefaultToolStripMenuItem.Name = "DefaultToolStripMenuItem"
        Me.DefaultToolStripMenuItem.Size = New System.Drawing.Size(125, 24)
        Me.DefaultToolStripMenuItem.Text = "default"
        '
        'CerrarToolStripMenuItem
        '
        Me.CerrarToolStripMenuItem.Name = "CerrarToolStripMenuItem"
        Me.CerrarToolStripMenuItem.Size = New System.Drawing.Size(59, 24)
        Me.CerrarToolStripMenuItem.Text = "cerrar"
        '
        'txtAbcAzul
        '
        Me.txtAbcAzul.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtAbcAzul.Location = New System.Drawing.Point(118, 145)
        Me.txtAbcAzul.Multiline = True
        Me.txtAbcAzul.Name = "txtAbcAzul"
        Me.txtAbcAzul.Size = New System.Drawing.Size(416, 36)
        Me.txtAbcAzul.TabIndex = 8
        '
        'txtAbcVerde
        '
        Me.txtAbcVerde.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtAbcVerde.Location = New System.Drawing.Point(118, 96)
        Me.txtAbcVerde.Multiline = True
        Me.txtAbcVerde.Name = "txtAbcVerde"
        Me.txtAbcVerde.Size = New System.Drawing.Size(416, 36)
        Me.txtAbcVerde.TabIndex = 9
        '
        'txtAbcRojo
        '
        Me.txtAbcRojo.Font = New System.Drawing.Font("Microsoft Sans Serif", 18.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.txtAbcRojo.Location = New System.Drawing.Point(118, 45)
        Me.txtAbcRojo.Multiline = True
        Me.txtAbcRojo.Name = "txtAbcRojo"
        Me.txtAbcRojo.Size = New System.Drawing.Size(416, 36)
        Me.txtAbcRojo.TabIndex = 10
        '
        'Timer1
        '
        '
        'Timer2
        '
        '
        'Timer3
        '
        '
        'LinkLabel1
        '
        Me.LinkLabel1.AutoSize = True
        Me.LinkLabel1.Location = New System.Drawing.Point(134, 135)
        Me.LinkLabel1.Name = "LinkLabel1"
        Me.LinkLabel1.Size = New System.Drawing.Size(77, 17)
        Me.LinkLabel1.TabIndex = 11
        Me.LinkLabel1.TabStop = True
        Me.LinkLabel1.Text = "LinkLabel1"
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(696, 249)
        Me.Controls.Add(Me.LinkLabel1)
        Me.Controls.Add(Me.txtAbcRojo)
        Me.Controls.Add(Me.txtAbcVerde)
        Me.Controls.Add(Me.txtAbcAzul)
        Me.Controls.Add(Me.txtResultado)
        Me.Controls.Add(Me.txtAzul)
        Me.Controls.Add(Me.txtVerde)
        Me.Controls.Add(Me.txtRojo)
        Me.Controls.Add(Me.btnAzul)
        Me.Controls.Add(Me.btnVerde)
        Me.Controls.Add(Me.btnRojo)
        Me.Controls.Add(Me.MenuStrip1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MainMenuStrip = Me.MenuStrip1
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "Form1"
        Me.Text = "RGB"
        Me.MenuStrip1.ResumeLayout(False)
        Me.MenuStrip1.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents btnRojo As System.Windows.Forms.Button
    Friend WithEvents btnVerde As System.Windows.Forms.Button
    Friend WithEvents btnAzul As System.Windows.Forms.Button
    Friend WithEvents txtRojo As System.Windows.Forms.TextBox
    Friend WithEvents txtVerde As System.Windows.Forms.TextBox
    Friend WithEvents txtAzul As System.Windows.Forms.TextBox
    Friend WithEvents txtResultado As System.Windows.Forms.TextBox
    Friend WithEvents MenuStrip1 As System.Windows.Forms.MenuStrip
    Friend WithEvents ProgresoToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents Barra1ToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents Barra2ToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents Barra3ToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents FondoToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ColorToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents DefaultToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents CerrarToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents txtAbcAzul As System.Windows.Forms.TextBox
    Friend WithEvents txtAbcVerde As System.Windows.Forms.TextBox
    Friend WithEvents txtAbcRojo As System.Windows.Forms.TextBox
    Friend WithEvents Timer1 As System.Windows.Forms.Timer
    Friend WithEvents Timer2 As System.Windows.Forms.Timer
    Friend WithEvents Timer3 As System.Windows.Forms.Timer
    Friend WithEvents LinkLabel1 As System.Windows.Forms.LinkLabel

End Class
