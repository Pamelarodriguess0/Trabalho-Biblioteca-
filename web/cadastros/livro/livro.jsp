<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">Livros</h1>
    <p class="mb-4">Cadastro de Livros</p>
     <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/LivroNovo">
        <i class="fas fa-sticky-note"></i>
        <strong>Novo Livro</strong>
    </a>
    <div class="card shadow">
        <div class="card-body">
            <table id="datatable" class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-left">Nome Livro</th>
                        <th class="text-center">ISBN</th>
                        <th class="text-left">Autor</th>
                        <th class="text-center">Data Publicação</th>
                        <th class="text-right">Valor Livro</th>
                        <th Align="text-rigth">Excluir</th>
                        <th Align="text-right">Alterar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="livro" items="${livros}">
                        <tr>
                            <td class="text-center">${livro.id}</td>
                            <td class="text-left">${livro.nomeLivro}</td>
                            <td class="text-center">${livro.isbn}</td>
                            <td class="text-left">${livro.autor}</td>
                            <td class="text-center">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${livro.dataPublicacao}" />
                            </td>
                            <td class="text-right">
                                <fmt:formatNumber value="${livro.valorLivro}" type="currency"/>
                            </td>
                            <td align="text-center">
                                <a class="btn btn-secondary btn-sm mb-1"href="#" id="deletar" title="Excluir" onclick="deletar(${livro.id})">
                                     <strong>Excluir</strong>
                                </a>
                            </td>                        
                            <td align="text-center">
                                <a class="btn btn-secondary btn-sm mb-1" href="${pageContext.request.contextPath}/LivroCarregar?id=${livro.id}">
                                    <strong>Alterar</strong>
                                </a>
                            </td>             
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        $('#datatable').DataTable({
            "oLanguage": {
                "sProcessing": "Processando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Nenhum registro encontrado.",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sSearch": "Buscar:",
                "oPaginate": {
                    "sFirst": "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext": "Próximo",
                    "sLast": "Último"
                }
            }
        });
    });
    
</script>

<%@include file="/footer.jsp"%>