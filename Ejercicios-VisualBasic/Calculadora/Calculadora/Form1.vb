Public Class Calculadora
    Private Sub Calculadora_Load(ByVal sender As Object, ByVal e As EventArgs) Handles MyBase.Load
        CajaOperaciones.ReadOnly = True
    End Sub

    Private Sub Botones(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button0.Click, Button1.Click, Button2.Click, Button3.Click, Button4.Click, Button5.Click, Button6.Click, Button7.Click, Button8.Click, Button9.Click, ButtonDelete.Click, ButtonDiv.Click, ButtonExp.Click, ButtonIntro.Click, ButtonMas.Click, ButtonMenos.Click, ButtonMultip.Click, ButtonRaiz.Click, ButtonRetroceder.Click, ButtonDec.Click, Parentesis1.Click, Parentesis2.Click
        Dim botonClicado As Button = CType(sender, Button)
        Select Case botonClicado.Name
            Case "Button0"
                CajaOperaciones.AppendText("0")
            Case "Button1"
                CajaOperaciones.AppendText("1")
            Case "Button2"
                CajaOperaciones.AppendText("2")
            Case "Button3"
                CajaOperaciones.AppendText("3")
            Case "Button4"
                CajaOperaciones.AppendText("4")
            Case "Button5"
                CajaOperaciones.AppendText("5")
            Case "Button6"
                CajaOperaciones.AppendText("6")
            Case "Button7"
                CajaOperaciones.AppendText("7")
            Case "Button8"
                CajaOperaciones.AppendText("8")
            Case "Button9"
                CajaOperaciones.AppendText("9")
            Case "ButtonMas"
                CajaOperaciones.AppendText("+")
            Case "ButtonMenos"
                CajaOperaciones.AppendText("-")
            Case "ButtonMultip"
                CajaOperaciones.AppendText("x")
            Case "ButtonDiv"
                CajaOperaciones.AppendText("/")
            Case "ButtonRaiz"
                CajaOperaciones.AppendText("√")
            Case "ButtonExp"
                CajaOperaciones.AppendText("^")
            Case "Parentesis1"
                CajaOperaciones.AppendText("(")
            Case "Parentesis2"
                CajaOperaciones.AppendText(")")
            Case "ButtonDec"
                CajaOperaciones.AppendText(")")
            Case "ButtonIntro"
                CalcularResultado()
            Case "ButtonRetroceder"
                If CajaOperaciones.Text.Length > 0 Then
                    CajaOperaciones.Text = CajaOperaciones.Text.Substring(0, CajaOperaciones.Text.Length - 1)
                End If
            Case ("ButtonDelete")
                CajaOperaciones.Clear()
        End Select
    End Sub

    Private Sub CalcularResultado()
        Try
            Dim expresion As String = CajaOperaciones.Text

            If expresion.Contains("√") Then
                Dim numero As Double = Double.Parse(expresion.Replace("√", "").Trim())
                CajaOperaciones.Text = Math.Sqrt(numero).ToString()
                Return
            End If

            If expresion.Contains("^") Then
                Dim partes As String() = expresion.Split("^"c)
                Dim baseNum As Double = Double.Parse(partes(0).Trim())
                Dim exponente As Double = Double.Parse(partes(1).Trim())
                CajaOperaciones.Text = Math.Pow(baseNum, exponente).ToString()
                Return
            End If

            Dim resultado As Double = New DataTable().Compute(expresion.Replace("x", "*"), Nothing)
            CajaOperaciones.Text = resultado.ToString()
        Catch ex As Exception
            MessageBox.Show("Error en la expresión: " & ex.Message)
        End Try
    End Sub
End Class
