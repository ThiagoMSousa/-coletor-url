<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Coletor de URL</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body>

	<!-- Modal -->
    <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="modalLabel">Excluir URL</h4>
                </div>
                <div class="modal-body">
                    Deseja realmente excluir essa URL?
                </div>
                <div class="modal-footer">
                    <form action="ManterUrl.do" method="post">
                        <input type="hidden" name="id" id="id_excluir" />
                        <button type="submit" class="btn btn-primary" name="acao" value="Excluir">Sim</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- /.modal -->
  
    <!-- Barra superior com os menus de navegação -->
    <c:import url="menu.jsp" />
    
    <!-- Container Principal -->
    <div id="main" class="container">
        <form action="listar_urls.do" method="post">
            <div id="top" class="row">
                <div class="col-md-3">
                    <h2>Coletor de URL's</h2>
                </div>

                <div class="col-md-6">
                    <div class="input-group h2">
                        <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar URLs (deixe vazio para trazer todos)">
                        <span class="input-group-btn">
        <button class="btn btn-primary" type="submit">
            <span class="glyphicon glyphicon-search"></span>
                        </button>
                        </span>
                    </div>
                </div>

                <div class="col-md-3">
                    <a href="VerificarUrl.jsp" class="btn btn-primary pull-right h2">Verificar Nova URL</a>
                </div>
            </div>
            <!-- /#top -->
        </form>
        <hr />
        
        <jsp:useBean id="lista" class="to.ListaDeUrls" scope="request" />
        <c:if test="${not empty lista}">
            <c:if test="${not empty lista.urls}">
                <div id="list" class="row">
                    <div class="table-responsive col-md-12">
                        <table class="table table-striped" cellspacing="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Endereço</th>
                                    <th class="actions">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="url" items="${lista.urls}">
                                    <tr>
                                        <td>
                                            ${url.id }
                                        </td>
                                        <td>
                                            ${url.url }
                                        </td>
                                        <td class="actions">
                                            <a class="btn btn-success btn-xs" href="ManterUrl.do?acao=Visualizar&id=${url.id }">Visualizar</a>
                                            <!-- 
                                            <a class="btn btn-warning btn-xs" href="ManterUrl.do?acao=Editar&id=${url.id }">Editar</a>
                                             -->
                                            <button id="btn${url.id }" type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#delete-modal" data-url="${url.id}">Excluir</button>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
                <!-- /#list -->
            </c:if>
        </c:if>

    </div>


    <!-- /#main -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $("#delete-modal").on('show.bs.modal', function(event) {
            var button = $(event.relatedTarget); //botao que disparou a modal
            var recipient = button.data('url');
            $("#id_excluir").val(recipient);
        });
    </script>
</body>

</html>