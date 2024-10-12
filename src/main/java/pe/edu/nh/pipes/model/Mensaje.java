package pe.edu.nh.pipes.model;

public class Mensaje {
    private String contenido;
    private String autor;

    @Override
    public String toString() {
        return "Mensaje{" +
                "contenido='" + contenido + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }

    private Mensaje() {
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
