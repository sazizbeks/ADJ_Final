package kz.edu.astanait.repositories.interfaces;

import kz.edu.astanait.models.Group;

import javax.ws.rs.BadRequestException;

public interface IGroupRepository extends CRD<Group> {
    void update(Group oldGroup,Group newGroup) throws BadRequestException;
}
