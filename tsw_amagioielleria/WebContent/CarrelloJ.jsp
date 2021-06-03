<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
<STYLE >
	table {
		font-family: arial, sans-serif;
		border-collapse: collapse;
		width: 70%; <%-- grandezza della tabella --%>
	}

	td, th {
		border: 1px solid #dddddd;
		text-align: center;
		padding: 8px;
	}

	tr:nth-child(even) {
		background-color: #dddddd;
	}
</STYLE>
</head>

<body>
	<%
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/progettods");
		Connection conn = null;
		PreparedStatement pstmt;
		String IDOrdine = "";
		String IDProdotto = "";
		int Quantita=0;
		String IVA_Effettiva = "";
		float prezzo_EffettivoUnitario=0f ;
		float Prezzo_totale=0f;
		  
		
		

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(
					"select IDOrdine, IDProdotto, Quantità, IVA_Effettiva, prezzo_EffettivoUnitario from voceordine ");
			pstmt.execute();

			ResultSet rs = pstmt.getResultSet();

			while (rs.next()) {
				IDOrdine = rs.getString(1);
				IDProdotto = rs.getString(2);
				Quantita = rs.getInt(3);
				IVA_Effettiva = rs.getString(4);
				prezzo_EffettivoUnitario = rs.getFloat(5);
				 Prezzo_totale= (Quantita)*(prezzo_EffettivoUnitario)  ;
			}
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	%>
	<%
		//out.print("Risultato : "+result);
	
	%>
	
	<h2 align=center> AMA GIOIELLERIA </h2>
	<TABLE align=center>
		<TR> 
			<th> Prodotto </th>
			<th> Nome </th>
			<th> Quantità </th>
			<th> Prezzo </th>
			<th> IVA</th>
			<th> Prezzo Totale</th>
			
		</TR>
		
		<TR> 
			<td> <%=IDProdotto%> </td>
			<td> <%=IDOrdine %>  </td>

			
			<td> <input type="number" id="tentacles" name="tentacles" min="0" max="10" value="<%=Quantita%>"></td>
			<td> <LABEL id="PrezzoUnitario" ><%=prezzo_EffettivoUnitario%></LABEL> </td>
			<td><%=IVA_Effettiva %></td>
			<td>  <LABEL id="Totale" ><%= Prezzo_totale %> </LABEL></td>
		</tr>
		
		
		
	</TABLE>


<SCRIPT type="text/javascript">

var selectElement = document.getElementById('tentacles');

selectElement.addEventListener('change', (event) => {
	debugger;
	var numPezzi = document.getElementById('tentacles').value;
	var prezzo = document.getElementById('PrezzoUnitario').innerHTML;
	document.getElementById('Totale').innerHTML=numPezzi*prezzo;

	
});
</SCRIPT>
</body>
</html>