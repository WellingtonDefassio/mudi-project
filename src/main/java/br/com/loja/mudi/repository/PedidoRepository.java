package br.com.loja.mudi.repository;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Cacheable("home")
    List<Pedido> findByStatusPedido(StatusPedido status, Pageable pageable);

    @Query("select p from Pedido p join p.user u where u.username = :username")
    List<Pedido> findAllByUser(@Param("username")String username);

    @Query("select p from Pedido p join p.user u where u.username = :username and p.statusPedido = :status")
    List<Pedido> findByUsernameAndStatusPedido(@Param("username") String username, @Param("status") StatusPedido status);
}
