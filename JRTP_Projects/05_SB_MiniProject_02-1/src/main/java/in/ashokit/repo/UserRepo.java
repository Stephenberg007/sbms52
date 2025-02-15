package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Integer>{
	public UserEntity findByUserEmailAndUserPwd(String email, String password);
	public UserEntity findByUserEmail(String email);
}
