
package org.authservice;


import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>
{

	Optional<UserAccount> findByUsername( String username );
}
