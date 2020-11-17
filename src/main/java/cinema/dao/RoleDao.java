package cinema.dao;

import cinema.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}
