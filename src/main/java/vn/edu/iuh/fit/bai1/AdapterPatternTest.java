package vn.edu.iuh.fit.bai1;

// Target Interface (JSON API)
interface JsonAPI {
    void sendJsonRequest(String jsonData);
}

// Adaptee (Hệ thống chỉ hỗ trợ XML)
class XmlAPI {
    public void sendXmlRequest(String xmlData) {
        System.out.println("📤 Gửi yêu cầu XML: " + xmlData);
    }
}

// Adapter (Chuyển đổi JSON -> XML)
class XmlToJsonAdapter implements JsonAPI {
    private XmlAPI xmlAPI;

    public XmlToJsonAdapter(XmlAPI xmlAPI) {
        this.xmlAPI = xmlAPI;
    }

    @Override
    public void sendJsonRequest(String jsonData) {
        // Chuyển đổi JSON -> XML (giả lập)
        String xmlData = convertJsonToXml(jsonData);
        xmlAPI.sendXmlRequest(xmlData);
    }

    private String convertJsonToXml(String jsonData) {
        return "<xml><data>" + jsonData.replace("{", "").replace("}", "").replace("\"", "") + "</data></xml>";
    }
}

// Main class để kiểm thử
public class AdapterPatternTest {
    public static void main(String[] args) {
        JsonAPI jsonAPI = new XmlToJsonAdapter(new XmlAPI());
        String jsonRequest = "{\"name\": \"Triet\", \"age\": 22}";
        jsonAPI.sendJsonRequest(jsonRequest);
    }
}

