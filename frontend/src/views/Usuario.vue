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
            class="elevation-1"
          >
            <template v-slot:item.role="{ item }">
                {{ getRole(item.role) }}
            </template>

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
                      @click="dialog = true"
                    >
                      Nuevo Usuario
                    </v-btn>
                  </template>

                <form >
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
                                  readonly="onEdit"
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
                                  v-model="editedItem.role"
                                  :items="types"
                                  item-text="text"
                                  item-value="value"
                                  label="Tipo"
                                ></v-select>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
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
                </form>
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

  export default {
    data: () => ({
      search: '',
        loading: false,
      dialog: false,
      dialogDelete: false,
        types: [ {
            text: 'Cliente',
            value: "ROLE_CLIENTE"
        }
        , {
              text:  'Empleado',
              value: "ROLE_EMPLEADO"
            }
        ],
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
          email: '',
          password: '',
          role: ''
      },
      defaultItem: {
          username: '',
          email: '',
          password: '',
          role: ''
      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Nuevo Usuario' : 'Editar Usuario'
      },
        onEdit() {
            return this.editedIndex !== -1;
        }
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
        getRole(role) {
            if(role === "ROLE_EMPLEADO") {
                return "Empleado";
            } else {
                return "Cliente"
            }
        },
        getAllUser() {
            window.axios.get(this.$store.state.apiUrl+"user/usuarios")
                .then(ans => {
                    this.users = ans.data;
                    console.log(ans.data);
                })
                .catch(err => {
                    console.log(err);
                })
        },

      editItem (item) {
        this.dialog = true
        this.editedIndex = this.users.indexOf(item)
        this.editedItem = item;
        this.editedItem.password = '';
        console.log(this.editedIndex);
      },

      deleteItem (item) {
          console.log(item);
        this.editedIndex = this.users.indexOf(item)
        this.editedItem = item
        this.dialogDelete = true
      },

      deleteItemConfirm () {
          window.axios.delete("usuario/deleteUser/" + this.editedItem.username)
              .then(ans => {
                    this.users.splice(this.editedIndex, 1)
                    this.closeDelete()
              }).catch(err => this.closeDelete())
      },

      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = this.defaultItem
          this.editedIndex = -1
        })
      },

      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = this.defaultItem
          this.editedIndex = -1
        })
      },

      save () {
          this.loading = true;
        if (this.editedIndex > -1) {
            console.log(this.editItem);
            this.users[this.editedIndex] = this.editItem;
            this.close();
        } else {
            window.axios.post(this.$store.state.apiUrl + "user/usuarios/createUser",this.editedItem)
                .then(ans => {
                    this.loading = false;
                    this.users.push(ans.data);
                    this.editItem = this.defaultItem;
                    this.close()
                })
                .catch(err => {
                    this.loading = false;
                    alert("Hubo un error");
                })
        }
      },
    },
  }
</script>
