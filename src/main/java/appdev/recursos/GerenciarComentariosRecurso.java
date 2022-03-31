package appdev.recursos;

import appdev.dominio.Comentario;
import appdev.dominio.ComentarioRequisicao;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Essa classe Recurso faz tratamento do Caso de Uso: Gerenciar Comentarios.
 *
 * Incluir
 * POST: /comentarios
 * 200: ok
 * 400: bad request: descricao não veio ou está vazio
 * 404: not found: não encontrou ou Pessoa ou TipoDeProblema
 *
 * Alterar
 * PUT: /comentarios
 * 200: ok
 * 400: bad request: id ou descricao não veio ou está vazio
 * 404: not found : não encontrou a Denuncia ou o TipoDeProblema
 *
 * Remover
 * DELETE: /comentarios
 * 200: ok
 * 400: bad request: id não veio ou está vazio
 * 403: forbidden: encontrou Comentario ou Solucao
 * 404: not found: não encontrou a Denuncia
 *
 */
@Path("/comentarios")
public class GerenciarComentariosRecurso {
    @POST
    public Response incluir(ComentarioRequisicao comentarioRequisicao){
        //--- verificamos se descricao, idPessoa, idDenuncia vieram
        if(comentarioRequisicao.descricao == null || comentarioRequisicao.descricao.length() == 0 ||
           comentarioRequisicao.idDenuncia == null || comentarioRequisicao.idDenuncia.length() == 0 ||
           comentarioRequisicao.idPessoa == null || comentarioRequisicao.idPessoa.length()==0 ){

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(Comentario.incluir(comentarioRequisicao)){
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    public Response alterar(ComentarioRequisicao comentarioRequisicao){
        //--- verificamos se id e descricao vieram
        if(comentarioRequisicao.descricao == null || comentarioRequisicao.descricao.length() == 0 ||
           comentarioRequisicao.id == null || comentarioRequisicao.id.length() == 0){

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(Comentario.alterar(comentarioRequisicao)){
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    public Response remover(ComentarioRequisicao comentarioRequisicao){
        //--- verificamos se id veio
        if(comentarioRequisicao.id == null || comentarioRequisicao.id.length() == 0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(Comentario.remover(comentarioRequisicao)){
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}