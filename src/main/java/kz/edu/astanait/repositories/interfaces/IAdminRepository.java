package kz.edu.astanait.repositories.interfaces;

import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Admin;

public interface IAdminRepository extends IRetrieve<Admin>{
    Admin login(String username, String password) throws NotFoundException;;
}
