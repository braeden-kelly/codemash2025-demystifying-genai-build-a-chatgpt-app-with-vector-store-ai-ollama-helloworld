package one.codemash25.ai.ollama.helloworld;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {

    private final ChatModel chatModel;

    @Autowired
    public OllamaController(OllamaChatModel chatModel) {
	this.chatModel = chatModel;
    }

    @GetMapping("/ai/ollama/chat")
    public String generate(@RequestParam(value = "message") String message) {
	ChatResponse response = chatModel.call(
        	new Prompt(
         		message,
	        	OllamaOptions.create()
	         		.withModel("gemma2")
		        	.withTemperature(0.4d)
		));
	System.out.println(response);
	return response.getResult().getOutput().getContent();
    }
}
