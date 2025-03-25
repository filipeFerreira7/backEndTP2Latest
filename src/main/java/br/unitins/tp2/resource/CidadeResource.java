package br.unitins.tp2.resource;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.tp2.dto.CidadeDTO;
import br.unitins.tp2.dto.CidadeDTOResponse;
import br.unitins.tp2.model.Cidade;
import br.unitins.tp2.service.CidadeService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.validation.Valid;

@Path("/cidades")
public class CidadeResource {

    @Inject
    public CidadeService cidadeService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(CidadeDTOResponse.valueOf(cidadeService.findById(id))).build();
    }
    @GET
    @Path("/search/{nome}")
    public List<CidadeDTOResponse> findByNome(@PathParam("nome")String nome){
        return cidadeService.findByNome(nome).
                stream().
                map(o -> CidadeDTOResponse.valueOf(o)).collect(Collectors.toList());

    }

    /*
    @GET
    public Response findAll(){
        return Response.ok(cidadeService.
                findAll(10,20).
                stream().
                map(o -> CidadeDTOResponse.valueOf(
                .toList()).build();

    }
*/
    @POST
    public Response create(@Valid CidadeDTO dto){
        return  Response.status(Response.Status.CREATED).entity(
                CidadeDTOResponse.valueOf(cidadeService.create(dto))
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id, @Valid CidadeDTO dto){
        cidadeService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        cidadeService.delete(id);
        return Response.noContent().build();
    }
}