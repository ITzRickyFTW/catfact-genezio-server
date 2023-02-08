import axios from 'axios';
import FormData from 'form-data'

(async () => {
    // TODO: Replace this with the base URL that was generated by the "genezio deploy" or "genezio local" command.
    // Example:
    // const url = "https://c53xgx2pv5nwal77f6qzzwrecm0dfmhv.lambda-url.us-east-1.on.aws";
    // or
    // const url = "http://127.0.0.1:8083"
    const url = "http://127.0.0.1:8083";

    if (url.length === 0) {
        console.error("Replace the \"url\" variable in test-webhook-example.js file with the base URL that was generated by the \"genezio deploy\" or \"genezio local\" command.")
        return;
    }

    // Send the query params request
    let response = await axios.get(url + "/HelloWorldHttpExample/handleQueryParams?name=john")
    console.log(response.data)

    // Send the request with plain text
    response = await axios.post(url + "/HelloWorldHttpExample/handleSimpleTextRequest", "text in body", { headers: { "content-type": "text/html" } });
    console.log(response.data)

    // Send the request with JSON body
    response = await axios.post(url + "/HelloWorldHttpExample/handleJsonBody", {
        name: "John"
    })
    console.log(response.data)

    // Send the request with multipart file
    const formData = new FormData();
    formData.append('myFile', "contents of file");
    
    response = await axios.post(url + "/HelloWorldHttpExample/handleMultipartData", formData, {
      headers: formData.getHeaders()
    });
    console.log(response.data)
})();
 
