package ir.ncttrade.resources;

import com.google.gson.Gson;
import ir.ncttrade.network.Api;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet("/live")
public class LivePriceServlet extends HttpServlet {
    private Gson gson;

    @Override
    public void init() throws ServletException {
        gson = new Gson();
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        resp.addHeader("Access-Control-Allow-Origin","*");
        LivePriceDTO livePriceDTO = gson.fromJson(req.getReader(), LivePriceDTO.class);
        try{
            Double price = Api.getForexPrice(livePriceDTO.getSymbol());
            if (price != null){
                livePriceDTO.setPrice(price);
                livePriceDTO.setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                out.println(
                        gson.toJson(
                                livePriceDTO
                        )
                );
            }
        }catch (Exception e){
            out.println(
                    gson.toJson(
                            LivePriceError.builder()
                                    .text("there is some problem please try again...")
                                    .time(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    )
            );
        }
    }

    @Builder
    @Data
    public static class LivePriceDTO{
        private String symbol;
        private Double price;
        private String time;
    }

    @Builder
    public static class LivePriceError{
        private String text;
        private String time;
    }
}
