
package webServiceClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServiceInterfaceImpl", targetNamespace = "http://ruyanfly/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiceInterfaceImpl {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "save", targetNamespace = "http://ruyanfly/", className = "ruyanfly.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://ruyanfly/", className = "ruyanfly.SaveResponse")
    @Action(input = "http://ruyanfly/ServiceInterfaceImpl/saveRequest", output = "http://ruyanfly/ServiceInterfaceImpl/saveResponse")
    public String save(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://ruyanfly/", className = "ruyanfly.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://ruyanfly/", className = "ruyanfly.SayHelloResponse")
    @Action(input = "http://ruyanfly/ServiceInterfaceImpl/sayHelloRequest", output = "http://ruyanfly/ServiceInterfaceImpl/sayHelloResponse")
    public String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}