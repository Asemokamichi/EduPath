package course.controller;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.controller.CourseController;
import com.asemokamichi.kz.edupath.dto.CourseDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@ContextConfiguration(classes = EduPathApplication.class)
public class CreateCourseTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Успешное создание нового курса")
    public void testCreateCourse_Success() throws Exception {
        CourseDTO courseDTO = new CourseDTO(
                "Introduction to Programming",
                "This course covers the basics of programming.",
                null);

        when(courseService.createCourse(any(CourseDTO.class))).thenReturn(new Course(courseDTO, new User()));

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(courseDTO.getTitle()))
                .andExpect(jsonPath("$.description").value(courseDTO.getDescription()))
                .andExpect(jsonPath("$.instructor_id").value(courseDTO.getInstructor_id()))
                .andReturn();
    }

    @Test
    @DisplayName("Создание курса с неверными данными")
    public void testCreateCourse_InvalidData() throws Exception {
        when(courseService.createCourse(any(CourseDTO.class)))
                .thenThrow(new InvalidRequest(ErrorMessage.INVALID_REQUEST.getMessage()));

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new CourseDTO())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value(new InvalidRequest(ErrorMessage.INVALID_REQUEST.getMessage()).getMessage()))
                .andReturn();
    }

}












