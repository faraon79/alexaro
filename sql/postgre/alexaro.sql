DROP TABLE IF EXISTS "neuron";

DROP TABLE IF EXISTS "layer";

DROP TABLE IF EXISTS "network";

CREATE TABLE "network" (
  id   SERIAL PRIMARY KEY   NOT NULL,
  name VARCHAR(50)          NOT NULL
);

CREATE TABLE "layer" (
  id          SERIAL PRIMARY KEY   NOT NULL,
  network_id  INT    REFERENCES network(id) NOT NULL,
  index       INT                  NOT NULL
);

CREATE TABLE "neuron" (
  id          SERIAL PRIMARY KEY   NOT NULL,
  layer_id  INT    REFERENCES layer(id) NOT NULL,
  index       INT                  NOT NULL
);

