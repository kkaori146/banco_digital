package br.com.cdb.bancodigita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cdb.bancodigita.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
