package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.services.PedidoUsuarioService;

@RestController
@RequestMapping("/pedidos-usuario")
public class PedidoUsuarioController {

    @Autowired
    private PedidoUsuarioService pedidoUsuarioService;

    // GET /pedidos-usuario
    @GetMapping
    public ResponseEntity<List<PedidoUsuario>> findAll() {
        return ResponseEntity.ok(pedidoUsuarioService.findAll());
    }

    // GET /pedidos-usuario/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PedidoUsuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoUsuarioService.findById(id));
    }

    // GET /pedidos-usuario/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PedidoUsuario>> findByUsuarioId(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(pedidoUsuarioService.findByUsuarioId(idUsuario));
    }

// ERROO 
    // GET /pedidos-usuario/funcionario/{idFuncionario}
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<List<PedidoUsuario>> findByFuncionarioId(@PathVariable Long idFuncionario) {
        return ResponseEntity.ok(pedidoUsuarioService.findByUsuarioId(idFuncionario));
    }//

    // POST /pedidos-usuario
    @PostMapping
    public ResponseEntity<PedidoUsuario> insert(@RequestBody PedidoUsuario pedidoUsuario) {
        return ResponseEntity.ok(pedidoUsuarioService.insert(pedidoUsuario));
    }

    // PUT /pedidos-usuario/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PedidoUsuario> update(@PathVariable Long id, @RequestBody PedidoUsuario pedidoAtualizado) {
        return ResponseEntity.ok(pedidoUsuarioService.update(id, pedidoAtualizado));
    }

    // DELETE /pedidos-usuario/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
