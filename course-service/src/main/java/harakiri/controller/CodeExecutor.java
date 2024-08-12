package harakiri.controller;

import harakiri.dto.request.ExecuteCodeRequest;
import harakiri.dto.response.ExecuteCodeResponse;
import harakiri.service.CodeExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/code")
public class CodeExecutor {
   private final CodeExecutorService codeExecutorService;

   @PostMapping("/execute")
   public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest){
      return  codeExecutorService.execute(executeCodeRequest);
   }

}
