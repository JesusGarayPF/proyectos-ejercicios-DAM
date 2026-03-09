Imports System.IO

Public Class Form1

    Dim a As usuario
    Dim estado As Boolean = False 'boton de borrado solo si un item esta seleccionado'
    Private Sub btnNuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnNuevo.Click
        abreLista()
    End Sub

    Private Sub btnBorrar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnBorrar.Click
        borrar()
    End Sub

    Private Sub btnLimpiar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnLimpiar.Click
        limpiar()
    End Sub

    Private Sub btnExportar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnExportar.Click
        exportar()
    End Sub

    Private Sub btnImportar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnImportar.Click
        importar()
    End Sub

    Private Sub btnSalir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSalir.Click
        salir()
    End Sub

    Private Sub btnSalvar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSalvar.Click
        cierraLlista()
    End Sub

    Private Sub ImportToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ImportToolStripMenuItem.Click
        importar()
    End Sub

    Private Sub ExportToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ExportToolStripMenuItem.Click
        exportar()
    End Sub

    Private Sub NuevoContactoToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles NuevoContactoToolStripMenuItem.Click
        abreLista()
    End Sub

    Private Sub BorrarContactoToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles BorrarContactoToolStripMenuItem.Click
        borrar()
    End Sub

    Private Sub LimpiarPanatallaToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles LimpiarPanatallaToolStripMenuItem.Click
        limpiar()
    End Sub

    Private Sub AyudaToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AyudaToolStripMenuItem.Click

    End Sub

    Private Sub SalirToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles SalirToolStripMenuItem.Click
        salir()
    End Sub

    Private Sub abreLista()
        ListBox1.Visible = False
        grpEntrada.Visible = True
        TextBox1.Text = ""
        TextBox2.Text = ""
        TextBox3.Text = ""
        boton2(False)
        '       btnBorrar.Enable = False
        btnNuevo.Enabled = False
        btnExportar.Enabled = False
    End Sub

    Private Sub borrar()
        Throw New NotImplementedException
    End Sub

    Private Sub limpiar()
        ListBox1.Items.Clear()
        estado = False
        pinta()
    End Sub

    Private Sub exportar()
        SaveFileDialog1.ShowDialog()
        If
    End Sub

    Private Sub importar()
        OpenFileDialog1.Filter = "txt files (*.txt)|*.txt"
        OpenFileDialog1.ShowDialog()
        'abrir archivo y cargar en listbox
        Dim linea As String
        ListBox1.Items.Clear()
        Dim oLector As New StreamReader(OpenFileDialog1.FileName)
        linea = oLector.ReadLine()
        Do While Not (linea Is Nothing)
            ListBox1.Items.Add(linea)
            linea = oLector.ReadLine()
        Loop
        oLector.Close()
        estado = False
        pinta()
    End Sub

    Private Sub salir()
        Throw New NotImplementedException
    End Sub

    Private Sub cierraLlista()
        botonBorrar(estado)
        ' btnBorrar.Enabled = estado
        btnNuevo.Enabled = True
        btnExportar.Enabled = True
    End Sub
    Private Sub addLista(ByVal a)
        ListBox1.Items.Add(a.out)
    End Sub
    Private Sub pinta()
        boton2(estado)
        '        btnBorrar.Enabled = estado
    End Sub
End Class
