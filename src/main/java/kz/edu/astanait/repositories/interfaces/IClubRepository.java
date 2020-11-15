package kz.edu.astanait.repositories.interfaces;

import kz.edu.astanait.models.Club;

public interface IClubRepository extends CRUD<Club>{
    void addStudentToClub(Integer club_id, Integer student_id);
}
