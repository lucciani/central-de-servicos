package io.github.lucciani.cs.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.lucciani.cs.domain.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
