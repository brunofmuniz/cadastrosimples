<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Contatos</title>
        <!-- --------------------------------------------------------------- -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap.min.js"></script>
        <!-- --------------------------------------------------------------- -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap.min.css"/>
        <!-- --------------------------------------------------------------- -->
        <script type="text/javascript">
            $(document).ready(function () {
                $('#contatos').DataTable({
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Portuguese-Brasil.json"
                    }
                });
            });
        </script>
    </head>
    <body style="margin: 20px">
        <h1>Cadastro de Contatos</h1>
        <hr />
        <form action="CadastrarContato" method="post">
            <table border="1" cellpadding="3" cellspacing="0">

                <tr>
                    <td>
                        Nome:
                    </td>
                    <td>
                        <input type="text" name="nome" 
                               maxlength="100" value="${contato.nome}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="text" name="email" 
                               maxlength="50"  value="${contato.email}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Telefone:
                    </td>
                    <td>
                        <input type="text" name="fone" 
                               maxlength="50" value="${contato.fone}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Salvar" />
                    </td>
                </tr>
            </table>
        </form>
        <c:if test="${not empty sucesso}">
            <h3 style="color: green">${sucesso}</h3>
        </c:if>
        <c:if test="${not empty erros}">
            <c:forEach var="erro" items="${erros}">
                <h3 style="color: red">${erro}</h3>
            </c:forEach>
        </c:if> 
        <br />
        <jsp:useBean 
            id="repository" 
            scope="page" 
            class="br.com.cadastro.infra.ContatoRepository" 
            />
        <c:set var="contatos" value="${repository.listar()}" />
        <c:if test="${not empty contatos}">
            <div style="width: 600px">
                <table id="contatos" class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Nome</th><th>Email</th><th>Telefone</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="contato" items="${contatos}">
                            <tr>
                                <td>${contato.nome}</td><td>${contato.email}</td><td>${contato.fone}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </body>
</html>
