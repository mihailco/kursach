package harakiri.repository.Impl;

import harakiri.dto.request.SearchCourseRequest;
import harakiri.model.CourseCollection;
import harakiri.repository.CourseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseDaoImpl implements CourseDao {
   private final MongoTemplate mongoTemplate;


   @Override
   public List<CourseCollection> search(SearchCourseRequest searchRequest) {
      Criteria criteria = new Criteria();

      if (searchRequest.getTittle() != null && !searchRequest.getTittle().isBlank()) {
         criteria = criteria.and("tittle").regex(searchRequest.getTittle(), "i");
      }

      if (searchRequest.getFio() != null && !searchRequest.getFio().isBlank()) {
         criteria = criteria.and("FIO").regex(searchRequest.getFio(), "i");
      }

      if (searchRequest.getYear() != null) {
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
         Date startDate, endDate;
         try {
            startDate = dateFormat.parse(searchRequest.getYear() + "-01-01T00:00:00.000Z");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.YEAR, 1);
            endDate = calendar.getTime();
            criteria = criteria.and("createdAt").gt(startDate).lte(endDate);
         } catch (Exception e) {
         }


      }

      Query query = new Query(criteria);

      return mongoTemplate.find(query, CourseCollection.class);
   }
}
