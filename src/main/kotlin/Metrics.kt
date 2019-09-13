package service

fun initMetrics() {
    io.prometheus.client.exporter.HTTPServer(9102, false)
}