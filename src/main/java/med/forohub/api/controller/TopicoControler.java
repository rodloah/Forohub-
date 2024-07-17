package med.forohub.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import med.forohub.api.domain.topic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoControler {

    @Autowired
    private ServiceTopic serviceTopic;
    @Autowired
    private TopicoRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> addTopic(@RequestBody @Valid DatosTopico data, HttpServletRequest request, UriComponentsBuilder uriBuilder) {
        var response = serviceTopic.addTopico(data, request);
        var res = new RespuestaTopico(response);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(res);
    }

    @GetMapping
    public ResponseEntity<Page<RespuestaTopico>> getTopics(@PageableDefault(sort = "creation", direction = Sort.Direction.ASC, size = 10) Pageable page) {
        return ResponseEntity.ok(topicRepository.findAll(page).map(RespuestaTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Topico>> getTopic(@PathVariable Long id) {
        Optional<Topico> topico = topicRepository.findById(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetallesTopico> updateTopic(@PathVariable Long id, @Valid @RequestBody ActualizarTopico data, HttpServletRequest request){
        var response = serviceTopic.update(id, data,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id,HttpServletRequest request){
        serviceTopic.delete(id,request);
        return ResponseEntity.noContent().build();
    }
}
