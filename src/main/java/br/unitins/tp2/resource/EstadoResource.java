package br.unitins.tp2.resource;

import java.util.List;

import br.unitins.tp2.dto.EstadoDTO;
import br.unitins.tp2.dto.EstadoDTOResponse;
import br.unitins.tp2.dto.PaginacaoResponseDTO;
import br.unitins.tp2.model.Estado;
import br.unitins.tp2.service.EstadoService;
import br.unitins.tp2.service.EstadoServiceImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService service;


    @GET
    public Response buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
       Long count = service.count();

        PaginacaoResponseDTO<EstadoDTOResponse> paginacao = PaginacaoResponseDTO.valueOf(count,page,pageSize,service.findAll(page,pageSize)
                .stream().map(EstadoDTOResponse::valueOf).toList());

        return Response.ok(paginacao).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(String nome, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return Response.ok(service.findByNome(nome, page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Estado buscarPorId(Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/sigla/{sigla}")
    public Estado buscarPorSigla(String sigla) { 
        return service.findBySigla(sigla);
    }

    @POST
    public Estado incluir(EstadoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, Estado estado) {
        service.update(id, estado);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

    @GET
    @Path("/count")
    public long total(Long id){
        return service.count();
    }

    @GET
    @Path("/nome/{nome}/count")
    public long totalPorNome(String nome){
        return service.count(nome);
    }
    @GET
    @Path("/countFilter")
    public long countFiltrados(@QueryParam("nome") String nome) {
        return service.countFiltrados(nome);
    }
}
