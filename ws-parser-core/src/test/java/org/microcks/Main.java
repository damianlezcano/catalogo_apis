package org.microcks;

import java.util.List;

import org.microcks.model.node.Node;
import org.microcks.model.node.NodeMethod;
import org.microcks.model.node.NodeRoot;

public class Main {

	public static void main(String[] args) throws Exception {
		m();
	}	
	
	public static Node m() throws Exception {
		String yaml = "---\n" + 
				"openapi: 3.0.2\n" + 
				"info:\n" + 
				"  title: petstore\n" + 
				"  version: 1.0.0\n" + 
				"  description: petstore\n" + 
				"  termsOfService: http://swagger.io/terms/\n" + 
				"  contact:\n" + 
				"    name: Swagger API Team\n" + 
				"    url: http://swagger.io\n" + 
				"    email: apiteam@swagger.io\n" + 
				"  license:\n" + 
				"    name: Apache 2.0\n" + 
				"    url: https://www.apache.org/licenses/LICENSE-2.0.html\n" + 
				"servers:\n" + 
				"- url: http://petstore.swagger.io/api\n" + 
				"paths:\n" + 
				"  /pets:\n" + 
				"    get:\n" + 
				"      parameters:\n" + 
				"      - style: form\n" + 
				"        name: tags\n" + 
				"        description: tags to filter by\n" + 
				"        schema:\n" + 
				"          type: array\n" + 
				"          items:\n" + 
				"            type: string\n" + 
				"        in: query\n" + 
				"        required: false\n" + 
				"      - name: limit\n" + 
				"        description: maximum number of results to return\n" + 
				"        schema:\n" + 
				"          format: int32\n" + 
				"          type: integer\n" + 
				"        in: query\n" + 
				"        required: false\n" + 
				"      responses:\n" + 
				"        \"200\":\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                type: array\n" + 
				"                items:\n" + 
				"                  $ref: '#/components/schemas/Pet'\n" + 
				"              examples:\n" + 
				"                laurent_cats:\n" + 
				"                  value:\n" + 
				"                  - id: 1\n" + 
				"                    name: Zaza\n" + 
				"                    tag: cat\n" + 
				"                  - id: 2\n" + 
				"                    name: Tigresse\n" + 
				"                    tag: cat\n" + 
				"                  - id: 3\n" + 
				"                    name: Maki\n" + 
				"                    tag: cat\n" + 
				"                  - id: 4\n" + 
				"                    name: Toufik\n" + 
				"                    tag: cat\n" + 
				"          description: pet response\n" + 
				"        default:\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                $ref: '#/components/schemas/Error'\n" + 
				"          description: unexpected error\n" + 
				"      operationId: findPets\n" + 
				"      description: |\n" + 
				"        Returns all pets from the system that the user has access to\n" + 
				"    post:\n" + 
				"      requestBody:\n" + 
				"        description: Pet to add to the store\n" + 
				"        content:\n" + 
				"          application/json:\n" + 
				"            schema:\n" + 
				"              $ref: '#/components/schemas/NewPet'\n" + 
				"            examples:\n" + 
				"              tigresse:\n" + 
				"                value:\n" + 
				"                  name: Tigresse\n" + 
				"                  tag: cat\n" + 
				"        required: true\n" + 
				"      responses:\n" + 
				"        \"200\":\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                $ref: '#/components/schemas/Pet'\n" + 
				"              examples:\n" + 
				"                tigresse:\n" + 
				"                  value:\n" + 
				"                    id: 2\n" + 
				"                    name: Tigresse\n" + 
				"                    tag: cat\n" + 
				"          description: pet response\n" + 
				"        default:\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                $ref: '#/components/schemas/Error'\n" + 
				"          description: unexpected error\n" + 
				"      operationId: addPet\n" + 
				"      description: Creates a new pet in the store.  Duplicates are allowed\n" + 
				"  /pets/{id}:\n" + 
				"    get:\n" + 
				"      parameters:\n" + 
				"      - name: id\n" + 
				"        description: ID of pet to fetch\n" + 
				"        schema:\n" + 
				"          format: int64\n" + 
				"          type: integer\n" + 
				"        in: path\n" + 
				"        required: true\n" + 
				"        examples:\n" + 
				"          zaza:\n" + 
				"            value: 1\n" + 
				"      responses:\n" + 
				"        \"200\":\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                $ref: '#/components/schemas/Pet'\n" + 
				"              examples:\n" + 
				"                zaza:\n" + 
				"                  value:\n" + 
				"                    id: 1\n" + 
				"                    name: Zaza\n" + 
				"                    tag: cat\n" + 
				"          description: pet response\n" + 
				"        default:\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                $ref: '#/components/schemas/Error'\n" + 
				"          description: unexpected error\n" + 
				"      operationId: findPetById\n" + 
				"      description: |-\n" + 
				"        Returns a user based on a single ID, if the user does not have\n" + 
				"        access to the pet\n" + 
				"    delete:\n" + 
				"      parameters:\n" + 
				"      - name: id\n" + 
				"        description: ID of pet to delete\n" + 
				"        schema:\n" + 
				"          format: int64\n" + 
				"          type: integer\n" + 
				"        in: path\n" + 
				"        required: true\n" + 
				"      responses:\n" + 
				"        \"204\":\n" + 
				"          description: pet deleted\n" + 
				"        default:\n" + 
				"          content:\n" + 
				"            application/json:\n" + 
				"              schema:\n" + 
				"                $ref: '#/components/schemas/Error'\n" + 
				"          description: unexpected error\n" + 
				"      operationId: deletePet\n" + 
				"      description: deletes a single pet based on the ID supplied\n" + 
				"    parameters:\n" + 
				"    - name: id\n" + 
				"      description: Pet identifier\n" + 
				"      schema:\n" + 
				"        type: integer\n" + 
				"      in: path\n" + 
				"      required: true\n" + 
				"components:\n" + 
				"  schemas:\n" + 
				"    Pet:\n" + 
				"      allOf:\n" + 
				"      - $ref: '#/components/schemas/NewPet'\n" + 
				"      - required:\n" + 
				"        - id\n" + 
				"        properties:\n" + 
				"          id:\n" + 
				"            format: int64\n" + 
				"            type: integer\n" + 
				"    NewPet:\n" + 
				"      required:\n" + 
				"      - name\n" + 
				"      properties:\n" + 
				"        name:\n" + 
				"          type: string\n" + 
				"        tag:\n" + 
				"          type: string\n" + 
				"    Error:\n" + 
				"      required:\n" + 
				"      - code\n" + 
				"      - message\n" + 
				"      properties:\n" + 
				"        code:\n" + 
				"          format: int32\n" + 
				"          type: integer\n" + 
				"        message:\n" + 
				"          type: string\n" + 
				"";
		
		String swagger = "{\"swagger\":\"2.0\",\"info\":{\"description\":\"This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.\",\"version\":\"1.0.3\",\"title\":\"Swagger Petstore\",\"termsOfService\":\"http://swagger.io/terms/\",\"contact\":{\"email\":\"apiteam@swagger.io\"},\"license\":{\"name\":\"Apache 2.0\",\"url\":\"http://www.apache.org/licenses/LICENSE-2.0.html\"}},\"host\":\"petstore.swagger.io\",\"basePath\":\"/v2\",\"tags\":[{\"name\":\"pet\",\"description\":\"Everything about your Pets\",\"externalDocs\":{\"description\":\"Find out more\",\"url\":\"http://swagger.io\"}},{\"name\":\"store\",\"description\":\"Access to Petstore orders\"},{\"name\":\"user\",\"description\":\"Operations about user\",\"externalDocs\":{\"description\":\"Find out more about our store\",\"url\":\"http://swagger.io\"}}],\"schemes\":[\"https\",\"http\"],\"paths\":{\"/pet/{petId}\":{\"get\":{\"tags\":[\"pet\"],\"summary\":\"Find pet by ID\",\"description\":\"Returns a single pet\",\"operationId\":\"getPetById\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"petId\",\"in\":\"path\",\"description\":\"ID of pet to return\",\"required\":true,\"type\":\"integer\",\"format\":\"int64\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"$ref\":\"#/definitions/Pet\"}},\"400\":{\"description\":\"Invalid ID supplied\"},\"404\":{\"description\":\"Pet not found\"}},\"security\":[{\"api_key\":[]}]},\"post\":{\"tags\":[\"pet\"],\"summary\":\"Updates a pet in the store with form data\",\"description\":\"\",\"operationId\":\"updatePetWithForm\",\"consumes\":[\"application/x-www-form-urlencoded\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"petId\",\"in\":\"path\",\"description\":\"ID of pet that needs to be updated\",\"required\":true,\"type\":\"integer\",\"format\":\"int64\"},{\"name\":\"name\",\"in\":\"formData\",\"description\":\"Updated name of the pet\",\"required\":false,\"type\":\"string\"},{\"name\":\"status\",\"in\":\"formData\",\"description\":\"Updated status of the pet\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"405\":{\"description\":\"Invalid input\"}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}]},\"delete\":{\"tags\":[\"pet\"],\"summary\":\"Deletes a pet\",\"description\":\"\",\"operationId\":\"deletePet\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"api_key\",\"in\":\"header\",\"required\":false,\"type\":\"string\"},{\"name\":\"petId\",\"in\":\"path\",\"description\":\"Pet id to delete\",\"required\":true,\"type\":\"integer\",\"format\":\"int64\"}],\"responses\":{\"400\":{\"description\":\"Invalid ID supplied\"},\"404\":{\"description\":\"Pet not found\"}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}]}},\"/pet/{petId}/uploadImage\":{\"post\":{\"tags\":[\"pet\"],\"summary\":\"uploads an image\",\"description\":\"\",\"operationId\":\"uploadFile\",\"consumes\":[\"multipart/form-data\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"petId\",\"in\":\"path\",\"description\":\"ID of pet to update\",\"required\":true,\"type\":\"integer\",\"format\":\"int64\"},{\"name\":\"additionalMetadata\",\"in\":\"formData\",\"description\":\"Additional data to pass to server\",\"required\":false,\"type\":\"string\"},{\"name\":\"file\",\"in\":\"formData\",\"description\":\"file to upload\",\"required\":false,\"type\":\"file\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"$ref\":\"#/definitions/ApiResponse\"}}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}]}},\"/pet\":{\"post\":{\"tags\":[\"pet\"],\"summary\":\"Add a new pet to the store\",\"description\":\"\",\"operationId\":\"addPet\",\"consumes\":[\"application/json\",\"application/xml\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"in\":\"body\",\"name\":\"body\",\"description\":\"Pet object that needs to be added to the store\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/Pet\"}}],\"responses\":{\"405\":{\"description\":\"Invalid input\"}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}]},\"put\":{\"tags\":[\"pet\"],\"summary\":\"Update an existing pet\",\"description\":\"\",\"operationId\":\"updatePet\",\"consumes\":[\"application/json\",\"application/xml\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"in\":\"body\",\"name\":\"body\",\"description\":\"Pet object that needs to be added to the store\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/Pet\"}}],\"responses\":{\"400\":{\"description\":\"Invalid ID supplied\"},\"404\":{\"description\":\"Pet not found\"},\"405\":{\"description\":\"Validation exception\"}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}]}},\"/pet/findByStatus\":{\"get\":{\"tags\":[\"pet\"],\"summary\":\"Finds Pets by status\",\"description\":\"Multiple status values can be provided with comma separated strings\",\"operationId\":\"findPetsByStatus\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"status\",\"in\":\"query\",\"description\":\"Status values that need to be considered for filter\",\"required\":true,\"type\":\"array\",\"items\":{\"type\":\"string\",\"enum\":[\"available\",\"pending\",\"sold\"],\"default\":\"available\"},\"collectionFormat\":\"multi\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/Pet\"}}},\"400\":{\"description\":\"Invalid status value\"}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}]}},\"/pet/findByTags\":{\"get\":{\"tags\":[\"pet\"],\"summary\":\"Finds Pets by tags\",\"description\":\"Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.\",\"operationId\":\"findPetsByTags\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"tags\",\"in\":\"query\",\"description\":\"Tags to filter by\",\"required\":true,\"type\":\"array\",\"items\":{\"type\":\"string\"},\"collectionFormat\":\"multi\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/Pet\"}}},\"400\":{\"description\":\"Invalid tag value\"}},\"security\":[{\"petstore_auth\":[\"write:pets\",\"read:pets\"]}],\"deprecated\":true}},\"/store/inventory\":{\"get\":{\"tags\":[\"store\"],\"summary\":\"Returns pet inventories by status\",\"description\":\"Returns a map of status codes to quantities\",\"operationId\":\"getInventory\",\"produces\":[\"application/json\"],\"parameters\":[],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"type\":\"object\",\"additionalProperties\":{\"type\":\"integer\",\"format\":\"int32\"}}}},\"security\":[{\"api_key\":[]}]}},\"/store/order/{orderId}\":{\"get\":{\"tags\":[\"store\"],\"summary\":\"Find purchase order by ID\",\"description\":\"For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions\",\"operationId\":\"getOrderById\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"orderId\",\"in\":\"path\",\"description\":\"ID of pet that needs to be fetched\",\"required\":true,\"type\":\"integer\",\"maximum\":10,\"minimum\":1,\"format\":\"int64\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"$ref\":\"#/definitions/Order\"}},\"400\":{\"description\":\"Invalid ID supplied\"},\"404\":{\"description\":\"Order not found\"}}},\"delete\":{\"tags\":[\"store\"],\"summary\":\"Delete purchase order by ID\",\"description\":\"For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors\",\"operationId\":\"deleteOrder\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"orderId\",\"in\":\"path\",\"description\":\"ID of the order that needs to be deleted\",\"required\":true,\"type\":\"integer\",\"minimum\":1,\"format\":\"int64\"}],\"responses\":{\"400\":{\"description\":\"Invalid ID supplied\"},\"404\":{\"description\":\"Order not found\"}}}},\"/store/order\":{\"post\":{\"tags\":[\"store\"],\"summary\":\"Place an order for a pet\",\"description\":\"\",\"operationId\":\"placeOrder\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"in\":\"body\",\"name\":\"body\",\"description\":\"order placed for purchasing the pet\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/Order\"}}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"$ref\":\"#/definitions/Order\"}},\"400\":{\"description\":\"Invalid Order\"}}}},\"/user/{username}\":{\"get\":{\"tags\":[\"user\"],\"summary\":\"Get user by user name\",\"description\":\"\",\"operationId\":\"getUserByName\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"username\",\"in\":\"path\",\"description\":\"The name that needs to be fetched. Use user1 for testing. \",\"required\":true,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"schema\":{\"$ref\":\"#/definitions/User\"}},\"400\":{\"description\":\"Invalid username supplied\"},\"404\":{\"description\":\"User not found\"}}},\"put\":{\"tags\":[\"user\"],\"summary\":\"Updated user\",\"description\":\"This can only be done by the logged in user.\",\"operationId\":\"updateUser\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"username\",\"in\":\"path\",\"description\":\"name that need to be updated\",\"required\":true,\"type\":\"string\"},{\"in\":\"body\",\"name\":\"body\",\"description\":\"Updated user object\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/User\"}}],\"responses\":{\"400\":{\"description\":\"Invalid user supplied\"},\"404\":{\"description\":\"User not found\"}}},\"delete\":{\"tags\":[\"user\"],\"summary\":\"Delete user\",\"description\":\"This can only be done by the logged in user.\",\"operationId\":\"deleteUser\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"username\",\"in\":\"path\",\"description\":\"The name that needs to be deleted\",\"required\":true,\"type\":\"string\"}],\"responses\":{\"400\":{\"description\":\"Invalid username supplied\"},\"404\":{\"description\":\"User not found\"}}}},\"/user/login\":{\"get\":{\"tags\":[\"user\"],\"summary\":\"Logs user into the system\",\"description\":\"\",\"operationId\":\"loginUser\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"name\":\"username\",\"in\":\"query\",\"description\":\"The user name for login\",\"required\":true,\"type\":\"string\"},{\"name\":\"password\",\"in\":\"query\",\"description\":\"The password for login in clear text\",\"required\":true,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"successful operation\",\"headers\":{\"X-Expires-After\":{\"type\":\"string\",\"format\":\"date-time\",\"description\":\"date in UTC when token expires\"},\"X-Rate-Limit\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"calls per hour allowed by the user\"}},\"schema\":{\"type\":\"string\"}},\"400\":{\"description\":\"Invalid username/password supplied\"}}}},\"/user/logout\":{\"get\":{\"tags\":[\"user\"],\"summary\":\"Logs out current logged in user session\",\"description\":\"\",\"operationId\":\"logoutUser\",\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[],\"responses\":{\"default\":{\"description\":\"successful operation\"}}}},\"/user\":{\"post\":{\"tags\":[\"user\"],\"summary\":\"Create user\",\"description\":\"This can only be done by the logged in user.\",\"operationId\":\"createUser\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"in\":\"body\",\"name\":\"body\",\"description\":\"Created user object\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/User\"}}],\"responses\":{\"default\":{\"description\":\"successful operation\"}}}},\"/user/createWithArray\":{\"post\":{\"tags\":[\"user\"],\"summary\":\"Creates list of users with given input array\",\"description\":\"\",\"operationId\":\"createUsersWithArrayInput\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"in\":\"body\",\"name\":\"body\",\"description\":\"List of user object\",\"required\":true,\"schema\":{\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/User\"}}}],\"responses\":{\"default\":{\"description\":\"successful operation\"}}}},\"/user/createWithList\":{\"post\":{\"tags\":[\"user\"],\"summary\":\"Creates list of users with given input array\",\"description\":\"\",\"operationId\":\"createUsersWithListInput\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\",\"application/xml\"],\"parameters\":[{\"in\":\"body\",\"name\":\"body\",\"description\":\"List of user object\",\"required\":true,\"schema\":{\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/User\"}}}],\"responses\":{\"default\":{\"description\":\"successful operation\"}}}}},\"securityDefinitions\":{\"api_key\":{\"type\":\"apiKey\",\"name\":\"api_key\",\"in\":\"header\"},\"petstore_auth\":{\"type\":\"oauth2\",\"authorizationUrl\":\"https://petstore.swagger.io/oauth/authorize\",\"flow\":\"implicit\",\"scopes\":{\"read:pets\":\"read your pets\",\"write:pets\":\"modify pets in your account\"}}},\"definitions\":{\"Category\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\",\"format\":\"int64\"},\"name\":{\"type\":\"string\"}},\"xml\":{\"name\":\"Category\"}},\"Pet\":{\"type\":\"object\",\"required\":[\"name\",\"photoUrls\"],\"properties\":{\"id\":{\"type\":\"integer\",\"format\":\"int64\"},\"category\":{\"$ref\":\"#/definitions/Category\"},\"name\":{\"type\":\"string\",\"example\":\"doggie\"},\"photoUrls\":{\"type\":\"array\",\"xml\":{\"wrapped\":true},\"items\":{\"type\":\"string\",\"xml\":{\"name\":\"photoUrl\"}}},\"tags\":{\"type\":\"array\",\"xml\":{\"wrapped\":true},\"items\":{\"xml\":{\"name\":\"tag\"},\"$ref\":\"#/definitions/Tag\"}},\"status\":{\"type\":\"string\",\"description\":\"pet status in the store\",\"enum\":[\"available\",\"pending\",\"sold\"]}},\"xml\":{\"name\":\"Pet\"}},\"Tag\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\",\"format\":\"int64\"},\"name\":{\"type\":\"string\"}},\"xml\":{\"name\":\"Tag\"}},\"ApiResponse\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\"},\"type\":{\"type\":\"string\"},\"message\":{\"type\":\"string\"}}},\"Order\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\",\"format\":\"int64\"},\"petId\":{\"type\":\"integer\",\"format\":\"int64\"},\"quantity\":{\"type\":\"integer\",\"format\":\"int32\"},\"shipDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"status\":{\"type\":\"string\",\"description\":\"Order Status\",\"enum\":[\"placed\",\"approved\",\"delivered\"]},\"complete\":{\"type\":\"boolean\"}},\"xml\":{\"name\":\"Order\"}},\"User\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\",\"format\":\"int64\"},\"username\":{\"type\":\"string\"},\"firstName\":{\"type\":\"string\"},\"lastName\":{\"type\":\"string\"},\"email\":{\"type\":\"string\"},\"password\":{\"type\":\"string\"},\"phone\":{\"type\":\"string\"},\"userStatus\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"User Status\"}},\"xml\":{\"name\":\"User\"}}},\"externalDocs\":{\"description\":\"Find out more about Swagger\",\"url\":\"http://swagger.io\"}}";
		
		NodeRoot nodeRootOpenApi = new NodeRoot();
		nodeRootOpenApi.load(swagger);
//		Node nodeOpenApi = nodeRootOpenApi.get("petstore").get("1.0.0");
//		System.out.println(nodeOpenApi.getPath());
		
		List<NodeMethod> methods = nodeRootOpenApi.getAllNodeMethod();

		for (NodeMethod nodeMethod : methods) {
			System.out.println(nodeMethod.getPath());
		}
		
//		Node nodeRootSwagger = new NodeRootSwaggerBean(swagger);
//		Node nodeSwagger = nodeRootSwagger.get("currency").get("1.3");
//		System.out.println(nodeSwagger);
//		
//		System.out.println(nodeOpenApi.equals(nodeSwagger));
		
		return nodeRootOpenApi;
		
//		System.out.println(nodeRoot);
		
//		HashMap info = (HashMap) item.get("info");
//		
//		String title = (String) info.get("title");
//		String version = (String) info.get("version");
//		
//		Node nodeService = new NodeServiceBean(title);
//		
//		HashMap paths = (HashMap) item.get("paths");
//		
//		Node nodeVersion = new NodeVersionBean(version, paths);
//		nodeService.add(nodeVersion);
//		
//		Node nodeRoot = new NodeRootBean();
//		nodeRoot.add(nodeService);
//		
//		System.out.println(nodeRoot.get());

	}
}
