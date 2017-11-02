
package org.authservice;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@Entity
@Table( name = "USER_ACCOUNT" )
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class UserAccount
{

	@Id
	@GeneratedValue
	private long id;

	@NonNull
	private String username;

	@NonNull
	private String password;

	@NonNull
	private String role;

	private boolean active;
}
