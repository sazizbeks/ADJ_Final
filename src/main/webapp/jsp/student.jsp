<%@include file="/jsp/index.components/header.jsp"%>
<script><%@include file="../js/student.ajax/showStudent.js"%></script>
<main class="main">

    <div class="grid">

            <input type="radio" class="radioButton" name="showStudents" id="showAll" value="showAll"> Show all <br>
            <input type="radio" class="radioButton" name="showStudents" id="showOne" value="showOne"> Show one <br>
            <input type="radio" class="radioButton" name="showStudents" id="showByGroup" value="showByGroup"> Show by group <br>
            <input type="radio" class="radioButton" name="showStudents" id="showByMajor" value="showByMajor"> Show by major <br>
            <input type="radio" class="radioButton" name="showStudents" id="showByYear" value="showByYear"> Show by year <br>

            <input type="text"  class="noneShow" name="showStudentsInput" id="IShowOne" placeholder="Show one by username">
            <input type="text"  class="noneShow" name="showStudentsInput" id="IShowByGroup" placeholder="Show by group">
            <input type="text"  class="noneShow" name="showStudentsInput" id="IShowByMajor" placeholder="Show by major">
            <input type="text"  class="noneShow" name="showStudentsInput" id="IShowByYear" placeholder="Show by year">
            <br>
            <input class="noneShow" id="submit" type="submit" value="Show">

        <span class="text-danger"  id="badResult"></span>
        <span class="text-success" id="goodResult"></span>

        <div id="information" class="noneShow">
            <div class="mb-4 card m-0 p-1 col-md-12" id="data">

            </div>
        </div>

    </div>

</main>

<%@include file="/jsp/index.components/footer.jsp"%>