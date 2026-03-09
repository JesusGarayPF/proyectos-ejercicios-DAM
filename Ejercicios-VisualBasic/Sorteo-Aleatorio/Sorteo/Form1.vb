Public Class Form1
    Private Sub Form1_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Label1.Text = "Introduce mínimo"
        Label2.Text = "Introduce máximo"
        Label3.Text = "Cálculo de un número entre un mínimo y un máximo"
        Label3.ForeColor = Color.DarkTurquoise
        Button1.Text = "Ejecutar"
        Me.ActiveControl = Button1
        Me.Text = "Sorteo de la PePa"
    End Sub
    Private Sub TextBox_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox1.TextChanged, TextBox2.TextChanged
        Label3.ForeColor = Color.Red
        If String.IsNullOrEmpty(TextBox1.Text) Then
            Label3.Text = "La primera entrada está vacía"
        ElseIf String.IsNullOrEmpty(TextBox2.Text) Then
            Label3.Text = "La segunda entrada está vacía"
        ElseIf (String.IsNullOrEmpty(TextBox1.Text) And String.IsNullOrEmpty(TextBox2.Text)) Then
            Label3.Text = "Introduce los datos de entrada"
        ElseIf Not IsNumeric(TextBox1.Text) Then
            Label3.Text = "La primera entrada no es numérica"
        ElseIf Not IsNumeric(TextBox2.Text) Then
            Label3.Text = "La segunda entrada no es numérica"
        Else
            Dim numero1 As Double = CDbl(TextBox1.Text)
            Dim numero2 As Double = CDbl(TextBox2.Text)
            If numero1 >= numero2 Then
                Label3.Text = "El segundo número debe ser mayor que el primero"
            Else
                Label3.Text = ""
            End If
        End If
    End Sub
    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Try
            Dim minimo As Integer = CInt(TextBox1.Text)
            Dim maximo As Integer = CInt(TextBox2.Text)
            If minimo > maximo Then
                Return
            End If
            Dim numeroGenerado As Integer = GenerarNumeroAleatorio(minimo, maximo)
            Label3.ForeColor = Color.Black
            Label3.Text = "Yeahhh: " & numeroGenerado
        Catch
        End Try
    End Sub
    Public Function GenerarNumeroAleatorio(ByVal minimo As Integer, ByVal maximo As Integer) As Integer
        Dim rnd As New Random()
        Return rnd.Next(minimo, maximo + 1)
    End Function
End Class
