package com.restaurant.restaurant_backend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_backend.entity.Terminal;
import com.restaurant.restaurant_backend.repository.TerminalRepository;

@RestController
@RequestMapping("/api/terminals")
public class TerminalController {

    private final TerminalRepository terminalRepository;

    public TerminalController(
            TerminalRepository terminalRepository) {

        this.terminalRepository = terminalRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('TERMINAL_VIEW')")
    public List<Terminal> getAllTerminals() {

        return terminalRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('TERMINAL_CREATE')")
    public Terminal createTerminal(
            @RequestBody Terminal terminal) {

        return terminalRepository.save(terminal);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TERMINAL_DELETE')")
    public String deleteTerminal(
            @PathVariable Long id) {

        terminalRepository.deleteById(id);

        return "Terminal Deleted";
    }

}