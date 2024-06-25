package course.controller;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.controller.CourseController;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
import com.asemokamichi.kz.edupath.service.CourseService;
import course.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@ContextConfiguration(classes = EduPathApplication.class)
public class GetCourseTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    @DisplayName("Успешное получение курса по ID")
    public void testGetCourse_Success() throws Exception {
        when(courseService.findById(anyLong())).thenReturn(new Course());

        mockMvc.perform(get("/courses/{id}", 1l))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Курс не найден по ID")
    public void testGetCourse_Resourse_Not_Found() throws Exception {
        when(courseService.findById(anyLong()))
                .thenThrow(new ResourceNotFound(ErrorMessage.RESOURCE_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/courses/{id}", 1l))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
