<template>
    <div data-app>
        <v-card>
        <v-card-title>
            Usuarios 
              <v-spacer></v-spacer>
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
          <v-data-table
            :headers="headers"
            :items="users"
            :search="search"
            sort-by="calories"
            class="elevation-1"
          >
            <template v-slot:top>
              <v-toolbar
                flat
              >
                <v-dialog
                  v-model="dialog"
                  max-width="500px"
                  class="height"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="primary"
                      dark
                      class="mb-2"
                      v-bind="attrs"
                      v-on="on"
                    >
                      Nuevo Usuario
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">{{ formTitle }}</span>
                    </v-card-title>

                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col
                            cols="12"
                            sm="6"
                            md="4"
                          >
                            <v-text-field
                              v-model="editedItem.username"
                              label="Username"
                            ></v-text-field>
                          </v-col>
                          <v-col
                            cols="12"
                            sm="6"
                            md="4"
                          >
                            <v-text-field
                              v-model="editedItem.email"
                              label="Email"
                              type="email"
                            ></v-text-field>
                          </v-col>
                          <v-col
                            cols="12"
                            sm="6"
                            md="4"
                          >
                            <v-text-field
                              v-model="editedItem.password"
                              label="Password"
                              type="password"
                            ></v-text-field>
                          </v-col>
                          <v-col
                            cols="12"
                            sm="6"
                            md="4"
                          >
                            <v-select
                              v-model="editedItem.type"
                              :items="types"
                              label="Tipo"
                            ></v-select>
                          </v-col>
                          <v-col
                            cols="12"
                            sm="6"
                            md="4"
                          >
                            <v-text-field
                              v-model="editedItem.name"
                              label="Nombre"
                            ></v-text-field>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        color="blue darken-1"
                        text
                        @click="close"
                      >
                        Cancel
                      </v-btn>
                      <v-btn
                        color="blue darken-1"
                        text
                        @click="save"
                      >
                        Save
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
                <v-dialog v-model="dialogDelete" max-width="500px">
                  <v-card>
                    <v-card-title class="headline">Estas seguro que quieres borrar este usuario?</v-card-title>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                      <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-icon
                small
                class="mr-2"
                @click="editItem(item)"
              >
                mdi-pencil
              </v-icon>
              <v-icon
                small
                @click="deleteItem(item)"
              >
                mdi-delete
              </v-icon>
            </template>
            <template v-slot:no-data>
              <v-btn
                color="primary"
                @click="initialize"
              >
                Reset
              </v-btn>
            </template>
          </v-data-table>
        </v-card>
    </div>
</template>

<script>
import axios from "axios"
import UsuarioForm from "../components/UsuarioForm";

  export default {
      components: {
          UsuarioForm
      },
    data: () => ({
        search: '',
      dialog: false,
      dialogDelete: false,
        types: ['Cliente', 'Empleado'],
      headers: [
        {
          text: 'Username',
          value: 'username',
        },
        { text: 'Email', value: 'email' },
        { text: 'Tipo', value: 'role' },
        { text: 'Actions', value: 'actions' },
      ],
      users: [
          {
            username: 'admin',
            role: 'admin',
            email: "admin",
          },
          {
            username: 'Miguel',
            role: 'maletaveras@gmail.com',
            email: "empleado",
          },
          {
            username: 'Juan',
            role: 'cliente',
            email: "cliente1@gmail.com",
          }
        ],
      editedIndex: -1,
      editedItem: {
          username: '',
          name: '',
          email: '',
          password: '',
          tipo: ''
      },
      defaultItem: {
          username: '',
          name: '',
          email: '',
          password: '',
          tipo: ''
      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Nuevo Usuario' : 'Editar Usuario'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

    created () {
      this.getAllUser()
    },

    methods: {
        getAllUser() {
            axios.get(this.$store.state.apiUrl+"/user/usuarios")
                .then(ans => {
                    this.users = ans.data;
                    console.log(ans.data);
                })
                .catch(err => {
                    console.log(err);
                })
        },

      editItem (item) {
        this.editedIndex = this.desserts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem (item) {
        this.editedIndex = this.desserts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },

      deleteItemConfirm () {
          axios.delete(this.$store.state.apiUrl+ "usuario/" + this.editedIndex)
              .then(ans => {
                    this.desserts.splice(this.editedIndex, 1)
                    this.closeDelete()
              })
      },

      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      save () {
        if (this.editedIndex > -1) {
          Object.assign(this.users[this.editedIndex], this.editedItem)
        } else {
            axios.post(this.$store.state.apiUrl + "user/usuarios/createUser",this.editedItem)
                .then(ans => {
                  this.users.push(this.editedItem)
                })
                .catch(err => {
                    alert("Hubo un error");
                })
        }
        this.close()
      },
    },
  }
</script>
