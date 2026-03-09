Public Class Form1
    Dim estadoRojo As Integer = 0
    Dim estadoVerde As Integer = 0
    Dim estadoAzul As Integer = 0

    Dim colorDialog As New ColorDialog()

    Private Sub Form1_Load(ByVal sender As Object, ByVal e As EventArgs) Handles MyBase.Load
        If txtRojo.Text = "" Then txtRojo.Text = "125"
        If txtVerde.Text = "" Then txtVerde.Text = "125"
        If txtAzul.Text = "" Then txtAzul.Text = "125"

        txtAbcRojo.Visible = False
        txtAbcVerde.Visible = False
        txtAbcAzul.Visible = False

        Timer1.Interval = 100
        Timer2.Interval = 100
        Timer3.Interval = 100
    End Sub

    Private Sub btnRojo_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnRojo.Click
        CambiarEstado(btnRojo, estadoRojo, Timer1)
    End Sub

    Private Sub btnVerde_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnVerde.Click
        CambiarEstado(btnVerde, estadoVerde, Timer2)
    End Sub

    Private Sub btnAzul_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnAzul.Click
        CambiarEstado(btnAzul, estadoAzul, Timer3)
    End Sub

    Private Sub CambiarEstado(ByVal btn As Button, ByRef estado As Integer, ByVal timer As Timer)
        If estado = 0 Then
            estado = 1
            btn.Text = ">>"
            timer.Start()
        ElseIf estado = 1 Then
            estado = -1
            btn.Text = "<<"
            timer.Start()
        Else
            estado = 0
            btn.Text = "="
            timer.Stop()
        End If
    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        ActualizarColor(txtAbcRojo, txtRojo, estadoRojo, 1, 0, 0)
    End Sub

    Private Sub Timer2_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer2.Tick
        ActualizarColor(txtAbcVerde, txtVerde, estadoVerde, 0, 1, 0)
    End Sub

    Private Sub Timer3_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer3.Tick
        ActualizarColor(txtAbcAzul, txtAzul, estadoAzul, 0, 0, 1)
    End Sub

    Private Sub ActualizarColor(ByVal txtAbecedario As TextBox, ByVal txtValor As TextBox, ByVal estado As Integer, ByVal r As Integer, ByVal g As Integer, ByVal b As Integer)
        Dim nuevoValor As Integer

        If Not Integer.TryParse(txtValor.Text, nuevoValor) Then
            nuevoValor = 125
        End If

        nuevoValor += estado * 5

        If nuevoValor >= 125 AndAlso nuevoValor <= 255 Then
            txtValor.Text = nuevoValor.ToString()
            txtValor.BackColor = Color.FromArgb(nuevoValor * r, nuevoValor * g, nuevoValor * b)

            Dim letras = "abcdefghijklmnopqrstuvwxyz"
            Dim numLetras = (nuevoValor - 125) \ 5
            txtAbecedario.Text = letras.Substring(0, Math.Min(numLetras, letras.Length))
        End If

        txtResultado.BackColor = Color.FromArgb(CInt(txtRojo.Text), CInt(txtVerde.Text), CInt(txtAzul.Text))
    End Sub

    Private Sub CerrarToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CerrarToolStripMenuItem.Click
        Application.Exit()
    End Sub

    Private Sub ColorToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ColorToolStripMenuItem.Click
        If colorDialog.ShowDialog() = DialogResult.OK Then
            Me.BackColor = colorDialog.Color
        End If
    End Sub

    Private Sub DefaultToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles DefaultToolStripMenuItem.Click
        Me.BackColor = DefaultBackColor
    End Sub

    Private Sub Barra1ToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Barra1ToolStripMenuItem.Click
        txtAbcRojo.Visible = True
        Barra1ToolStripMenuItem.Enabled = False
    End Sub

    Private Sub Barra2ToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Barra2ToolStripMenuItem.Click
        txtAbcVerde.Visible = True
        Barra2ToolStripMenuItem.Enabled = False
    End Sub

    Private Sub Barra3ToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Barra3ToolStripMenuItem.Click
        txtAbcAzul.Visible = True
        Barra3ToolStripMenuItem.Enabled = False
    End Sub
End Class
